package google.solution.service;

import google.solution.domain.User;
import google.solution.dto.GetUserRes;
import google.solution.dto.UpdateUserRes;

public interface UserService {

    public GetUserRes getUser(String id) throws Exception;

    public UpdateUserRes updateUser(User user) throws Exception;
}
