package google.solution.repository;

import google.solution.dto.GetUserRes;

public interface UserRepository {

    public GetUserRes getUser(String id) throws Exception;
}
