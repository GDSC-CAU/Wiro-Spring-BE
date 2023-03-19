package google.solution.service;

import google.solution.domain.Message;

public interface ChatService {

    public SendMessageRes sendMessage(String id, Message message) throws Exception;
}
