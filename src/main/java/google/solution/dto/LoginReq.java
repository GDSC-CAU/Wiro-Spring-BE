package google.solution.dto;

import lombok.Data;

@Data
public class LoginReq {
    // 정보들 추가
    private String nickname;
    private String username;
    private String email;
    private String blood;
    private String disease;
    private String medicine;
    private String id;
}
