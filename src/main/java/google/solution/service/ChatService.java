package google.solution.service;

import google.solution.domain.Message;
import google.solution.dto.SendMessageRes;

public interface ChatService {

    public SendMessageRes sendMessage(String id, Message message) throws Exception;

}
