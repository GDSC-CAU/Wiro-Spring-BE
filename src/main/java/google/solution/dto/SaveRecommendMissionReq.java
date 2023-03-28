package google.solution.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveRecommendMissionReq {

    String code;
    String content;

    public static SaveRecommendMissionReq createSaveRecommendMissionReq(String code, String content) {
        SaveRecommendMissionReq saveRecommendMissionReq = new SaveRecommendMissionReq();
        saveRecommendMissionReq.setCode(code);
        saveRecommendMissionReq.setContent(content);
        return saveRecommendMissionReq;
    }
}
