package google.solution.service;

import google.solution.domain.User;
import google.solution.dto.GetUserRes;
import google.solution.dto.LoginReq;
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
        User user = userRepository.getUser(id);
        if (user == null) {
            throw new Exception();
        }
        GetUserRes getUserRes = GetUserRes.userToGetUserRes(user);
        return getUserRes;
    }

    @Override
    public UpdateUserRes updateUser(String id, UpdateUserReq updateUserReq) throws Exception {
        return userRepository.updateUser(id, updateUserReq);
    }

    // 등록 코드
    public User register(String uid, String email, LoginReq userInformation) {
        User user = User.createUser(uid, email, userInformation);

        // 등록
        try {
            userRepository.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
