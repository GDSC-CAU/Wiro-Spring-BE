package google.solution.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateUserReq {

    private String nickname;
    private String email;
    private String blood;
    private String disease;
    private String medicine;
    private String id;
}
