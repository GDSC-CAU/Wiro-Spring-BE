package google.solution.domain;

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
}
