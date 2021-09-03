package heguang5460.github.io.message.biz.ex;

/**
 * BIZ 层业务异常
 * @author he guang
 * @date 2021/8/31 9:25
 */
public class BizException extends RuntimeException {

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }
}
