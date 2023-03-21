package google.solution.repository;


import google.solution.domain.Message;
import google.solution.dto.GetChatContentRes;
import google.solution.dto.GetChatMessageRes;
import google.solution.dto.GetChatRoomRes;
import google.solution.dto.SendMessageRes;

import java.util.List;

public interface ChatRepository {

    public SendMessageRes sendMessage(String id, Message message) throws Exception;
    public GetChatRoomRes getChatRooms(String id) throws Exception;
    public List<GetChatContentRes> getChatContent(String roomId, String id) throws Exception;
    public List<GetChatMessageRes> getChatMessages(String id) throws Exception;

    public String findUserId(String nickname) throws Exception;
    public String findUserNickname(String id) throws Exception;
}
