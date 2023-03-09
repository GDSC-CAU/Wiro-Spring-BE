package google.solution.controller;

import google.solution.domain.User;
import google.solution.dto.GetUserRes;
import google.solution.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public String updateUser(@RequestParam User user) throws Exception{
        return userService.updateUser(user);
    }



}
