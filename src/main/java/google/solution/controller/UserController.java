package google.solution.controller;

import google.solution.domain.User;
import google.solution.dto.GetUserRes;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;
import google.solution.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/getUserInfo/{userId}")
    ResponseEntity<GetUserRes> getUser(@PathVariable("userId") String userId) {
        try {
            GetUserRes userInfo = userService.getUser(userId);
            return ResponseEntity.ok().body(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/updateUserInfo")
    public ResponseEntity<UpdateUserRes> updateUser(@RequestParam UpdateUserReq user) throws Exception{
        UpdateUserRes updateUserRes = userService.updateUser(user);
        return ResponseEntity.ok().body(updateUserRes);
    }



}
