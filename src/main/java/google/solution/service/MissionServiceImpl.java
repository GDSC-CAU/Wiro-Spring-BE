package google.solution.service;

import google.solution.domain.User;
import google.solution.dto.GetMissionInfoRes;
import google.solution.dto.GetUserRes;
import google.solution.repository.UserRepository;
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
        GetMissionInfoRes getMissionInfoRes = missionRepository.getMissionInfo(code);
        return getMissionInfoRes;
    }
}
