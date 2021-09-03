package heguang5460.github.io.message.common.result;

import lombok.Getter;

/**
 * 返回码枚举
 *
 * @author he guang
 */
@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    PARAM_ERROR(400, "参数异常"),
    UNAUTHORIZED(401, "未授权"),
    FAILED(500, "操作失败");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
