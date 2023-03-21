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

    private String sourceId;
    private String content;
    private String destinationId;
    private String updateTime;

    public static GetChatMessageRes messageToGetChatMessageRes(Message message) {
        GetChatMessageRes getChatMessageRes = new GetChatMessageRes();
        getChatMessageRes.setSourceId(message.getSourceId());
        getChatMessageRes.setContent(message.getContent());
        getChatMessageRes.setDestinationId(message.getDestinationId());
        getChatMessageRes.setUpdateTime(message.getUpdateTime());
        return getChatMessageRes;
    }
}
