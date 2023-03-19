package google.solution.repository;


import google.solution.domain.Message;
import google.solution.dto.SendMessageRes;

public interface ChatRepository {

    public SendMessageRes sendMessage(String id, Message message) throws Exception;
}
