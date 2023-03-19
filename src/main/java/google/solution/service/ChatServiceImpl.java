package google.solution.service;

import google.solution.domain.Message;
import google.solution.domain.User;
import google.solution.dto.GetChatRoomsRes;
import google.solution.dto.SendMessageRes;
import google.solution.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public GetChatRoomsRes getChatRooms(String id) throws Exception {
        GetChatRoomsRes getChatRoomsRes = chatRepository.getChatRooms(id);
        return getChatRoomsRes;
    }

}
