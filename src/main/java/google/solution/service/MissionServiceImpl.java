package google.solution.service;

import google.solution.domain.Mission;
import google.solution.dto.GetMissionInfoRes;
import google.solution.dto.MissionCompleteReq;
import google.solution.dto.MissionCompleteRes;
import google.solution.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    @Override
    @Transactional(readOnly = true)
    public GetMissionInfoRes getMissionInfo(String code) throws Exception {
        Mission mission = missionRepository.getMissionInfo(code);
        GetMissionInfoRes getMissionInfoRes = GetMissionInfoRes.missionToGetMissionInfoRes(mission);
        return getMissionInfoRes;
    }

    @Override
    public MissionCompleteRes missionComplete(MissionCompleteReq missionCompleteReq) throws Exception {
        List<MissionCompleteReq> missions = missionRepository.getSuccessMissions(missionCompleteReq);
        if (missions.size() == 4) {
            missions.add(missionCompleteReq);
            calculateWeightedAverage(missions);
            MissionCompleteRes missionCompleteRes = missionRepository.saveMissions(missions);
        } else {
            missions.add(missionCompleteReq);
            MissionCompleteRes missionCompleteRes = missionRepository.saveMissions(missions);
        }
    }
}
