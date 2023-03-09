package google.solution.repository;

import google.solution.domain.User;
import google.solution.dto.GetUserRes;
import google.solution.dto.UpdateUserRes;

public interface UserRepository {

    public GetUserRes getUser(String id) throws Exception;

    public UpdateUserRes updateUser(User user) throws Exception;
}
