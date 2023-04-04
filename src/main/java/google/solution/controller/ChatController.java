package google.solution.controller;


import google.solution.dto.*;
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
    public BaseResponse<SendMessageRes> sendMessage(@RequestBody SendMessageReq message, Authentication authentication) {
        try {
            String id = authentication.getName();
            SendMessageRes sendMessageRes = chatService.sendMessage(id, message);
            return new BaseResponse<>(sendMessageRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

    @GetMapping("/showChatRooms")
    public BaseResponse<GetChatRoomRes> getChatRooms(Authentication authentication) {
        try {
            String id = authentication.getName();
            GetChatRoomRes getChatRoomsRes = chatService.getChatRooms(id);
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

    @GetMapping("/showChatMessages")
    public BaseResponse<List<GetChatMessageRes>> getChatMessages(Authentication authentication) {
        try {
            String id = authentication.getName();
            List<GetChatMessageRes> getChatMessageRes = chatService.getChatMessages(id);
            return new BaseResponse<>(getChatMessageRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }
}
