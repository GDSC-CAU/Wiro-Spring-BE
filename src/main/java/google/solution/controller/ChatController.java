package google.solution.controller;


import google.solution.domain.Message;
import google.solution.dto.SendMessageRes;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;
import google.solution.service.ChatService;
import google.solution.service.UserService;
import google.util.BaseResponse;
import google.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/sendMessage")
    public BaseResponse<SendMessageRes> sendMessage(@RequestBody Message message, Authentication authentication) {
        try {
            String id = authentication.getName();
            SendMessageRes sendMessageRes = chatService.sendMessage(id,message);
            return new BaseResponse<>(sendMessageRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
}
