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
            Double averageScore  = calculateWeightedAverage(missions);
            missionRepository.saveScore(averageScore);
            MissionCompleteRes missionCompleteRes = missionRepository.saveMissions(missions);
            return missionCompleteRes;
        } else {
            missions.add(missionCompleteReq);
            MissionCompleteRes missionCompleteRes = missionRepository.saveMissions(missions);
            return missionCompleteRes;
        }
    }

    private double calculateWeightedAverage(List<MissionCompleteReq> missions) {
        double averageScore = 0.0;
        for (int i = 0; i < missions.size(); i++) {
            averageScore = averageScore + (i+1) * missions.get(i).getScore();
        }
        averageScore = averageScore / 15;
        averageScore = Math.round((averageScore*1000) / 1000.0);

        return averageScore;
    }
}
