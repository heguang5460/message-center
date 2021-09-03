package heguang5460.github.io.message.web.exception;

import heguang5460.github.io.message.biz.ex.BizException;
import heguang5460.github.io.message.common.result.CommonResult;
import heguang5460.github.io.message.common.result.ResultCode;
import heguang5460.github.io.message.dao.ex.DaoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author he guang
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult validParamException(MethodArgumentNotValidException e) {
        return getCommonResult(e.getBindingResult());
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult validParamException(BindException e) {
        return getCommonResult(e.getBindingResult());
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public CommonResult validParamException(IllegalArgumentException e) {
        return CommonResult.failed(ResultCode.PARAM_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public CommonResult handle(BizException e) {
        log.error("操作失败 cause by = {}", e.getMessage());
        return CommonResult.failed(ResultCode.FAILED, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = DaoException.class)
    public CommonResult handle(DaoException e) {
        log.error("操作失败 cause by = {}", e.getMessage());
        return CommonResult.failed(ResultCode.FAILED, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult handle(Exception e) {
        log.error("操作失败 cause by = {}", e.getMessage());
        return CommonResult.failed();
    }

    private CommonResult getCommonResult(BindingResult bindingResult) {
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return CommonResult.failed(ResultCode.PARAM_ERROR, message);
    }

}
