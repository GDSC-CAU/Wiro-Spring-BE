package google.solution.domain;

import google.solution.dto.LoginReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    private String username;
    private String email;
    private String blood;
    private String disease;
    private String medicine;
    private String id;

    public User(LoginReq loginReq) {
        this.username = loginReq.getUsername();
        this.email = loginReq.getEmail();
        this.blood = loginReq.getBlood();
        this.disease = loginReq.getDisease();
        this.medicine = loginReq.getMedicine();
        this.id = loginReq.getId();
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
