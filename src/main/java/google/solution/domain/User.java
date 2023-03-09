package google.solution.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {

    private String username;
    private String email;
    private String nickname;

    public User(String username, String email, String nickname) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
    }
}
