package google.solution.repository;

import google.solution.dto.*;

import java.util.List;

public interface ChatRepository {

    public SendMessageRes sendMessage(String id, SendMessageReq message) throws Exception;
    public GetChatRoomRes getChatRooms(String id) throws Exception;
    public List<GetChatContentRes> getChatContent(String roomId, String id) throws Exception;
    public List<GetChatMessageRes> getChatMessages(String id) throws Exception;
}
