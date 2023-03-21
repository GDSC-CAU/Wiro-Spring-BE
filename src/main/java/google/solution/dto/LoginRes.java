package google.solution.dto;

import google.solution.domain.User;
import lombok.Data;

@Data
public class LoginRes {
    private String uid;
    private String email;
    private String nickname;

    public LoginRes(User user) {
        this.uid = user.getUsername();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}