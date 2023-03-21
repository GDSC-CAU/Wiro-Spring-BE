package google.solution.service;

import google.solution.domain.Message;
import google.solution.dto.GetChatContentRes;
import google.solution.dto.GetChatMessageRes;
import google.solution.dto.GetChatRoomRes;
import google.solution.dto.SendMessageRes;
import google.solution.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public SendMessageRes sendMessage(String id, Message message) throws Exception {
        SendMessageRes sendMessageRes = chatRepository.sendMessage(id, message);
        return sendMessageRes;
    }

    @Override
    public GetChatRoomRes getChatRooms(String id) throws Exception {
        GetChatRoomRes getChatRoomsRes = chatRepository.getChatRooms(id);
        return getChatRoomsRes;
    }

    @Override
    public List<GetChatContentRes> getChatContent(String roomId, String id) throws Exception {
        List<GetChatContentRes> getChatContentRes = chatRepository.getChatContent(roomId, id);
        return getChatContentRes;
    }

    @Override
    public List<GetChatMessageRes> getChatMessages(String id) throws Exception {
        List<GetChatMessageRes> getChatMessageRes = chatRepository.getChatMessages(id);
        return getChatMessageRes;
    }



}
