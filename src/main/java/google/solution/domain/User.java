package google.solution.domain;

import google.solution.dto.LoginReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    private String username;
    private String email;
    private String nickname;
    private String blood;
    private String disease;
    private String id;
    private String medicine;

    public User(String username, String email, String nickname) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
    }

    public static User createUser(String username, String email, LoginReq userInformation) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setNickname(userInformation.getNickname());
        user.setBlood(userInformation.getBlood());
        user.setDisease(userInformation.getDisease());
        user.setId(userInformation.getId());
        user.setMedicine(userInformation.getMedicine());
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
