package google.solution.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateUserReq {

    private String id;
    private String nickname;
    private String email;
}
