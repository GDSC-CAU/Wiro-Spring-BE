package google.solution.dto;

import google.solution.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetChatMessageRes {

    private String sourceNickname;
    private String content;
    private String destinationNickname;
    private String updateTime;
    private int isFromMe;

    public static GetChatMessageRes messageToGetChatMessageRes(Message message) {
        GetChatMessageRes getChatMessageRes = new GetChatMessageRes();
        getChatMessageRes.setSourceNickname(message.getSourceNickname());
        getChatMessageRes.setContent(message.getContent());
        getChatMessageRes.setDestinationNickname(message.getDestinationNickname());
        getChatMessageRes.setUpdateTime(message.getUpdateTime());
        getChatMessageRes.setIsFromMe(message.getIsFromMe());
        return getChatMessageRes;
    }
}
