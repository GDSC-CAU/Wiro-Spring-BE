package google.solution.repository;

import google.solution.dto.GetMissionInfoRes;

public interface MissionRepository {

    public GetMissionInfoRes getMissionInfo(String code) throws Exception;
}
