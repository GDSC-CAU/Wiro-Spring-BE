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
public class GetChatContentRes {

    private String sourceId;
    private String content;
    private String destinationId;
    private String updateTime;

    public static GetChatContentRes messageToGetChatContentRes(Message message) {
        GetChatContentRes getChatContentRes = new GetChatContentRes();
        getChatContentRes.setSourceId(message.getSourceNickname());
        getChatContentRes.setContent(message.getContent());
        getChatContentRes.setDestinationId(message.getDestinationNickname());
        getChatContentRes.setUpdateTime(message.getUpdateTime());
        return getChatContentRes;
    }
}
