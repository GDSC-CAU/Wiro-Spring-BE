package google.solution.repository;

import google.solution.domain.Mission;
import google.solution.domain.SuccessMission;
import google.solution.dto.GetMissionInfoRes;
import google.solution.dto.MissionCompleteReq;
import google.solution.dto.MissionCompleteRes;

import java.util.List;

public interface MissionRepository {

    public Mission getMissionInfo(String code) throws Exception;
    public List<SuccessMission> getSuccessMissions(MissionCompleteReq missionCompleteReq, String userId) throws Exception;
    public void saveScore(double score, String userId) throws Exception;
    public MissionCompleteRes saveMissions(List<SuccessMission> missions, String userId) throws Exception;
}
