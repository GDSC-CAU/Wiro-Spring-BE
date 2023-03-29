package google.solution.domain;

import google.solution.dto.SendMessageReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String sourceNickname;
    private String content;
    private String destinationNickname;
    private String updateTime;
    private int isFromMe;

    public static Message sendMessageReqToMessage(String sourceNickname, SendMessageReq sendMessageReq, int isFromMe) {
        Message message = new Message();
        message.setSourceNickname(sourceNickname);
        message.setContent(sendMessageReq.getContent());
        message.setDestinationNickname(sendMessageReq.getDestinationNickname());
        message.setUpdateTime(sendMessageReq.getUpdateTime());
        message.setIsFromMe(isFromMe);
        return message;
    }

    public String getSourceNickname() {
        return sourceNickname;
    }

    public void setSourceNickname(String sourceNickname) {
        this.sourceNickname = sourceNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDestinationNickname() {
        return destinationNickname;
    }

    public void setDestinationNickname(String destinationNickname) {
        this.destinationNickname = destinationNickname;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsFromMe() {
        return isFromMe;
    }

    public void setIsFromMe(int isFromMe) {
        this.isFromMe = isFromMe;
    }
}

