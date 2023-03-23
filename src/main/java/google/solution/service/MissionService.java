package google.solution.service;

import google.solution.dto.GetMissionInfoRes;
import google.solution.dto.GetUserRes;
import google.solution.dto.MissionCompleteReq;
import google.solution.dto.MissionCompleteRes;

public interface MissionService {

    public GetMissionInfoRes getMissionInfo(String code) throws Exception;
    public MissionCompleteRes missionComplete(MissionCompleteReq missionCompleteReq) throws Exception;
}
