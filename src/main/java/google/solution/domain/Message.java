package google.solution.domain;

import google.solution.dto.SendMessageReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {

    private String sourceNickname;
    private String content;
    private String destinationNickname;
    private String updateTime;
    private boolean isFromMe;

    public static Message sendMessageReqToMessage(String sourceNickname, SendMessageReq sendMessageReq, boolean isFromMe) {
        Message message = new Message();
        message.setSourceNickname(sourceNickname);
        message.setContent(sendMessageReq.getContent());
        message.setDestinationNickname(sendMessageReq.getDestinationNickname());
        message.setUpdateTime(sendMessageReq.getUpdateTime());
        message.setFromMe(isFromMe);
        return message;
    }
}

