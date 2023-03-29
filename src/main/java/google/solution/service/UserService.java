package google.solution.service;

import google.solution.domain.User;
import google.solution.dto.GetUserRes;
import google.solution.dto.LoginReq;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public GetUserRes getUser(String id) throws Exception;

    public UpdateUserRes updateUser(String id, UpdateUserReq updateUserReq) throws Exception;

    public User register(String uid, String email, LoginReq userInformation);

}
