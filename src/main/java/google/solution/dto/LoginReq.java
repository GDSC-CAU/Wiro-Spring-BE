package google.solution.dto;

import lombok.Data;

@Data
public class LoginReq {
    // 정보들 추가
    private String nickname;
    private String blood;
    private String disease;
    private String id;
    private String medicine;
}
