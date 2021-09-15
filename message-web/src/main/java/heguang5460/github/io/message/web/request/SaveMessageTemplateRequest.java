package heguang5460.github.io.message.web.request;

import heguang5460.github.io.message.web.validate.FlagValidator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author he guang
 * @date 2021/8/30 20:41
 */
@Data
public class SaveMessageTemplateRequest {

    /**
     * 场景码
     */
    @NotBlank(message = "场景码不得为空")
    @Length(max = 32, message = "场景码最大长度为32个字符")
    private String sceneCode;

    /**
     * 渠道码
     */
    @NotBlank(message = "渠道码不得为空")
    @FlagValidator(value = {"SMS", "APP_PUSH", "MESSAGE_BOX", "MAIL"}, message = "渠道码错误")
    private String channelCode;

    /**
     * 网关码
     */
    @NotBlank(message = "网关码不得为空")
    @FlagValidator(value = {"MWKJ", "ALIYUN", "JPUSH", "MESSAGE_BOX", "MAIL"}, message = "网关码错误")
    private String gatewayCode;

    /**
     * 模板内容
     */
    @NotBlank(message = "模板内容不得为空")
    private String templateContent;

    /**
     * 当前登录用户
     */
    @NotNull(message = "登录用户不得为空")
    private Long loginUserId;

}
