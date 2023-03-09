package google.solution.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public GetUserRes getUser(String id) throws Exception {
        return userRepository.getUser(id);
    }
}
