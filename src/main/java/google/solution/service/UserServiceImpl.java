package google.solution.service;

import google.solution.domain.User;
import google.solution.dto.GetUserRes;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;
import google.solution.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.getUser(username);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
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

    // 등록 코드
    public User register(String uid, String email, String nickname) {
        User user = new User(uid, email, nickname);

        // 등록
        try {
            userRepository.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
