package google.solution.repository;

import google.solution.domain.User;
import google.solution.dto.GetUserRes;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;

public interface UserRepository {

    public User getUser(String id) throws Exception;

    public UpdateUserRes updateUser(String id, UpdateUserReq updateUserReq) throws Exception;

    public String saveUser(User user) throws Exception;
}
