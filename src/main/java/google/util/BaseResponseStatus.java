package google.util;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    // 요청 성공
    SUCCESS(200, "Success"),

    // 실패 이유마다 추가
    FAIL(404, "Fail");

    private final int code;
    private final String message;

    private BaseResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
