package google.solution.dto;

import google.solution.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GetUserRes {

    private String email;
    private String nickname;
    private String blood;
    private String disease;
    private String id;
    private String medicine;

    // User 객체를 GetUserRes로 변환
    public static GetUserRes userToGetUserRes(User user) {
        GetUserRes getUserRes = new GetUserRes();
        getUserRes.setEmail(user.getEmail());
        getUserRes.setNickname(user.getNickname());
        getUserRes.setBlood(user.getBlood());
        getUserRes.setDisease(user.getDisease());
        getUserRes.setId(user.getId());
        getUserRes.setMedicine(user.getMedicine());
        return getUserRes;
    }
}
