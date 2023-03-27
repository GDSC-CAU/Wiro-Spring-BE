package google.solution.dto;

import google.solution.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GetUserRes {

    private String nickname;
    private String blood;
    private String disease;
    private String medicine;
    private String id;

    // User 객체를 GetUserRes로 변환
    public static GetUserRes userToGetUserRes(User user) {
        GetUserRes getUserRes = new GetUserRes();
        getUserRes.setNickname(user.getNickname());
        getUserRes.setBlood(user.getBlood());
        getUserRes.setDisease(user.getDisease());
        getUserRes.setMedicine(user.getMedicine());
        getUserRes.setId(user.getId());
        return getUserRes;
    }
}
