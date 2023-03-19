package google.solution.controller;


import google.solution.domain.Message;
import google.solution.dto.GetChatRoomsRes;
import google.solution.dto.SendMessageRes;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;
import google.solution.service.ChatService;
import google.solution.service.UserService;
import google.util.BaseResponse;
import google.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/sendMessage")
    public BaseResponse<SendMessageRes> sendMessage(@RequestBody Message message, Authentication authentication) {
        try {
            String id = authentication.getName();
            SendMessageRes sendMessageRes = chatService.sendMessage(id, message);
            return new BaseResponse<>(sendMessageRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

    @GetMapping("/showChatRooms")
    public BaseResponse<GetChatRoomsRes> getChatRooms(Authentication authentication) {
        try {
            String id = authentication.getName();
            GetChatRoomsRes getChatRoomsRes = chatService.getChatRooms(id);
            return new BaseResponse<>(getChatRoomsRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }
}
