package google.solution.repository;

import google.solution.domain.Mission;
import google.solution.dto.GetMissionInfoRes;

public interface MissionRepository {

    public Mission getMissionInfo(String code) throws Exception;
}
