package heguang5460.github.io.message.common.result;

import heguang5460.github.io.message.common.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回对象
 *
 * @author he guang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public static CommonResult success() {
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), Constants.EMPTY);
    }

    public static CommonResult success(Object data) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static CommonResult failed() {
        return new CommonResult(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), Constants.EMPTY);
    }

    public static CommonResult failed(ResultCode resultCode) {
        return new CommonResult(resultCode.getCode(), resultCode.getMessage(), Constants.EMPTY);
    }

    public static CommonResult failed(ResultCode resultCode, String message) {
        return new CommonResult(resultCode.getCode(), message, Constants.EMPTY);
    }

}
