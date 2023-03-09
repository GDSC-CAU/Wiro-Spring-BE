package google.solution.dto;

import lombok.Getter;

@Getter
public class UpdateUserReq {

    private String id;
    private String nickname;
    private String email;

    public UpdateUserReq() {
    }

    public UpdateUserReq(String id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}
