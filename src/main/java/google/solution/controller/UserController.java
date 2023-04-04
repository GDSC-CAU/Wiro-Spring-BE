package google.solution.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import google.solution.domain.User;
import google.solution.dto.*;
import google.solution.service.UserService;
import google.util.BaseResponse;
import google.util.BaseResponseStatus;
import google.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final FirebaseAuth firebaseAuth;
    private final UserService userService;

    @GetMapping("/getUserInfo")
    public BaseResponse<GetUserRes> getUser(Authentication authentication) {
        try {
            User user = ((User) authentication.getPrincipal());
            return new BaseResponse<>(GetUserRes.userToGetUserRes(user));
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

    @PostMapping("/updateUserInfo")
    public BaseResponse<UpdateUserRes> updateUser(@RequestBody UpdateUserReq updateUserReq, Authentication authentication) {
        try {
            String id = authentication.getName();
            UpdateUserRes updateUserRes = userService.updateUser(id, updateUserReq);
            return new BaseResponse<>(updateUserRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }

    }

    @PostMapping("/login")
    public BaseResponse<LoginRes> register(@RequestHeader("Authorization") String authorization,
                                           @RequestBody LoginReq loginReq) {
        // TOKEN을 가져온다.
        FirebaseToken decodedToken;
        try {
            String token = RequestUtil.getAuthorizationToken(authorization);
            decodedToken = firebaseAuth.verifyIdToken(token);
        } catch (IllegalArgumentException | FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }
        // 사용자가 있다면 기존 정보 리턴
        User user = ((User) userService.loadUserByUsername(decodedToken.getUid()));
        if (user != null) {
            LoginRes loginRes = new LoginRes(user);
            return new BaseResponse<>(loginRes);
        }
        // 사용자를 등록한다.
        User registeredUser = userService.register(
                decodedToken.getUid(), decodedToken.getEmail(), loginReq);
        LoginRes loginRes = new LoginRes(registeredUser);
        return new BaseResponse<>(loginRes);
    }
}
