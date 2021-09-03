package heguang5460.github.io.message.web.request;

import heguang5460.github.io.message.web.validate.FlagValidator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/8/31 14:13
 */
@Data
public class SaveMessageChannelRequest {

    /**
     * 渠道码
     */
    @NotBlank(message = "渠道码不得为空")
    @FlagValidator(value = {"SMS", "APP_PUSH", "MESSAGE_BOX", "MAIL"}, message = "渠道码错误")
    private String channelCode;

    /**
     * 登录用户
     */
    @NotNull(message = "登录用户不得为空")
    private Long loginUserId;

}
