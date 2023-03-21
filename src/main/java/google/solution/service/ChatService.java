package google.solution.service;

import google.solution.domain.Message;
import google.solution.dto.GetChatContentRes;
import google.solution.dto.GetChatRoomRes;
import google.solution.dto.SendMessageRes;

import java.util.List;

public interface ChatService {

    public SendMessageRes sendMessage(String id, Message message) throws Exception;
    public GetChatRoomRes getChatRooms(String id) throws Exception;
    public List<GetChatContentRes> getChatContent(String roomId, String id) throws Exception;


}
