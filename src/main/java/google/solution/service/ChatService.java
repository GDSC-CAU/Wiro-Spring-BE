package google.solution.service;

import google.solution.domain.Message;
import google.solution.dto.GetChatRoomsRes;
import google.solution.dto.SendMessageRes;

import java.util.List;

public interface ChatService {

    public SendMessageRes sendMessage(String id, Message message) throws Exception;
    public GetChatRoomsRes getChatRooms(String id) throws Exception;

}
