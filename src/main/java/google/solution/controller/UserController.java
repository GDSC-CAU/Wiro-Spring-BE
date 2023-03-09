package google.solution.controller;

import google.solution.dto.GetUserRes;
import google.solution.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
