package google.solution.service;

import google.solution.dto.*;

public interface MissionService {

    public GetMissionInfoRes getMissionInfo(String id, String code) throws Exception;
    public MissionCompleteRes missionComplete(MissionCompleteReq missionCompleteReq, String userId) throws Exception;
    public GetMissionHistoryRes getMissionHistory(String userId) throws Exception;
    public GetCheckListHistoryRes getCheckListHistory(String userId) throws Exception;
}
