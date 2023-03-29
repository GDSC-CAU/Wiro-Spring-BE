package google.solution.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import google.solution.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetChatContentRes {

    private String sourceNickname;
    private String content;
    private String destinationNickname;
    private String updateTime;
    private int isFromMe;

    public static GetChatContentRes messageToGetChatContentRes(Message message) {
        GetChatContentRes getChatContentRes = new GetChatContentRes();
        getChatContentRes.setSourceNickname(message.getSourceNickname());
        getChatContentRes.setContent(message.getContent());
        getChatContentRes.setDestinationNickname(message.getDestinationNickname());
        getChatContentRes.setUpdateTime(message.getUpdateTime());
        getChatContentRes.setIsFromMe(message.getIsFromMe());
        return getChatContentRes;
    }

}
