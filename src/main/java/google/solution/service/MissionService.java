package google.solution.service;

import google.solution.dto.GetMissionInfoRes;
import google.solution.dto.GetUserRes;

public interface MissionService {

    public GetMissionInfoRes getMissionInfo(String code) throws Exception;
}
