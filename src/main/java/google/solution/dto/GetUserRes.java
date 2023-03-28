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

    // User 객체를 GetUserRes로 변환
    public static GetUserRes userToGetUserRes(User user) {
        GetUserRes getUserRes = new GetUserRes();
        getUserRes.setNickname(user.getNickname());
        return getUserRes;
    }
}
