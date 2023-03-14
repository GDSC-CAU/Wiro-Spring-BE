package google.solution.service;

import google.solution.dto.GetUserRes;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;
import google.solution.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public GetUserRes getUser(String id) throws Exception {
        if (userRepository.getUser(id) == null) {
            throw new Exception();
        }
        return userRepository.getUser(id);
    }

    @Override
    public UpdateUserRes updateUser(UpdateUserReq user) throws Exception {
        return userRepository.updateUser(user);
    }

}
