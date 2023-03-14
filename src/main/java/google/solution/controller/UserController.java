package google.solution.controller;

import google.solution.dto.GetUserRes;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;
import google.solution.service.UserService;
import google.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/getUserInfo/{userId}")
    BaseResponse<GetUserRes> getUser(@PathVariable("userId") String userId) {
        try {
            GetUserRes userInfo = userService.getUser(userId);
            return new BaseResponse<>(userInfo);
        } catch (Exception e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("/updateUserInfo")
    public BaseResponse<UpdateUserRes> updateUser(@RequestBody UpdateUserReq user) throws Exception{
        try {
            UpdateUserRes updateUserRes = userService.updateUser(user);
            return new BaseResponse<>(updateUserRes);
        } catch (Exception e) {
            return new BaseResponse<>(e.getStatus());
        }

    }



}
