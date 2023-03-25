package google.solution.dto;

import google.solution.domain.Mission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetMissionInfoRes {

    private String content;

    public static GetMissionInfoRes missionToGetMissionInfoRes(Mission mission) {
        GetMissionInfoRes getMissionInfoRes = new GetMissionInfoRes();
        getMissionInfoRes.setContent(mission.getContent());
        return getMissionInfoRes;
    }
}
