package google.solution.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import google.solution.domain.User;
import google.solution.dto.*;
import google.solution.service.UserService;
import google.util.BaseResponse;
import google.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final FirebaseAuth firebaseAuth;
    private final UserService userService;

    @GetMapping("/getUserInfo/{userId}")
    BaseResponse<GetUserRes> getUser(@PathVariable("userId") String userId) {
        try {
            GetUserRes userInfo = userService.getUser(userId);
            return new BaseResponse<>(userInfo);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

    @PostMapping("/updateUserInfo")
    public BaseResponse<UpdateUserRes> updateUser(@RequestBody UpdateUserReq user) {
        try {
            UpdateUserRes updateUserRes = userService.updateUser(user);
            return new BaseResponse<>(updateUserRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }

    }

    @PostMapping("/login")
    public LoginRes register(@RequestHeader("Authorization") String authorization,
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
        // 사용자를 등록한다.
        User registeredUser = userService.register(
                decodedToken.getUid(), decodedToken.getEmail(), loginReq.getNickname());
        return new LoginRes(registeredUser);
    }



}
