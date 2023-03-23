package google.solution.service;

import google.solution.domain.Mission;
import google.solution.dto.GetMissionInfoRes;
import google.solution.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
