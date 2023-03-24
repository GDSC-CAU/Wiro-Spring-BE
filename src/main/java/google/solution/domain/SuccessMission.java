package google.solution.domain;

import google.solution.dto.MissionCompleteReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuccessMission {

    private String code;
    private double score;
    private String updateTime;

    public static SuccessMission missionCompleteReqToSuccessMission(MissionCompleteReq missionCompleteReq) {
        SuccessMission successMission = new SuccessMission();
        successMission.setCode(missionCompleteReq.getCode());
        successMission.setScore(missionCompleteReq.getScore());
        successMission.setUpdateTime(missionCompleteReq.getUpdateTime());
        return successMission;
    }
}
