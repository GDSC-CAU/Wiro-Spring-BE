package google.solution.repository;

import google.solution.domain.Mission;
import google.solution.domain.SuccessMission;
import google.solution.dto.GetCheckListHistoryRes;
import google.solution.dto.GetMissionHistoryRes;
import google.solution.dto.MissionCompleteReq;
import google.solution.dto.MissionCompleteRes;

import java.util.List;

public interface MissionRepository {

    public Mission getMissionInfo(String id, String code) throws Exception;
    public List<SuccessMission> getSuccessMissions(MissionCompleteReq missionCompleteReq, String userId) throws Exception;
    public void saveScore(String code, double score, String userId) throws Exception;
    public MissionCompleteRes saveMissions(List<SuccessMission> missions, String userId) throws Exception;
    public GetMissionHistoryRes getMissionHistory(String userId) throws Exception;
    public GetCheckListHistoryRes getCheckListHistory(String userId) throws Exception;

}
