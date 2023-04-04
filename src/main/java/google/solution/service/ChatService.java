package google.solution.service;

import google.solution.dto.*;

import java.util.List;

public interface ChatService {

    public SendMessageRes sendMessage(String id, SendMessageReq message) throws Exception;
    public GetChatRoomRes getChatRooms(String id) throws Exception;
    public List<GetChatContentRes> getChatContent(String roomId, String id) throws Exception;
    public List<GetChatMessageRes> getChatMessages(String id) throws Exception;

}
