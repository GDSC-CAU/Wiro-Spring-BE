package google.solution.service;

import google.solution.dto.GetUserRes;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;

public interface UserService {

    public GetUserRes getUser(String id) throws Exception;

    public UpdateUserRes updateUser(UpdateUserReq user) throws Exception;
}
