package google.solution.dto;

public class UpdateUserRes {

    private String message;
    private String updateTime;

    public UpdateUserRes() {
    }

    public UpdateUserRes(String message, String updateTime) {
        this.message = message;
        this.updateTime = updateTime;
    }
}
