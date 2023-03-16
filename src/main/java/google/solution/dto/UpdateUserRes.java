package google.solution.dto;

import lombok.Getter;

@Getter
public class UpdateUserRes {
    private String updateTime;

    public UpdateUserRes() {
    }

    public UpdateUserRes(String updateTime) {
        this.updateTime = updateTime;
    }
}
