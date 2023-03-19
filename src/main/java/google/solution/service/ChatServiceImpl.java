package google.solution.service;

import google.solution.domain.Message;
import google.solution.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatServiceImpl {

    private final ChatRepository chatRepository;

    @Override
    public SendMessageRes sendMessage(String id, Message message) throws Exception {
        SendMessageRes sendMessageRes = chatRepository.sendMessage(id, message);
        return sendMessageRes;
    }

}
