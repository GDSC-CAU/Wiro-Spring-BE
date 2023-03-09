package google.solution.service;

import google.solution.dto.GetUserRes;

public interface UserService {

    public GetUserRes getUser(String id) throws Exception;
}
