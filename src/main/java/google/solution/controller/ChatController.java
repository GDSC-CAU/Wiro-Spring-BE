package google.solution.controller;


import google.solution.domain.Message;
import google.solution.dto.GetChatContentRes;
import google.solution.dto.GetChatRoomRes;
import google.solution.dto.SendMessageRes;
import google.solution.service.ChatService;
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
    public BaseResponse<List<GetChatRoomRes>> getChatRooms(Authentication authentication) {
        try {
            String id = authentication.getName();
            List<GetChatRoomRes> getChatRoomsRes = chatService.getChatRooms(id);
            return new BaseResponse<>(getChatRoomsRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

    @GetMapping("/showChatRooms/{roomId}")
    public BaseResponse<List<GetChatContentRes>> getChatContent(@PathVariable String roomId, Authentication authentication) {
        try {
            String id = authentication.getName();
            List<GetChatContentRes> getChatContentRes = chatService.getChatContent(roomId, id);
            return new BaseResponse<>(getChatContentRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }
}
