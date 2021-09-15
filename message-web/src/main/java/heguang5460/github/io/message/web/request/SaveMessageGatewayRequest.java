package heguang5460.github.io.message.web.request;

import heguang5460.github.io.message.web.validate.FlagValidator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/8/31 14:47
 */
@Data
public class SaveMessageGatewayRequest {

    /**
     * 消息渠道码
     */
    @NotBlank(message = "渠道码不得为空")
    @FlagValidator(value = {"SMS", "APP_PUSH", "MESSAGE_BOX", "MAIL"}, message = "渠道码错误")
    private String channelCode;
    /**
     * 消息网关码
     */
    @NotBlank(message = "网关码不得为空")
    @FlagValidator(value = {"MWKJ_SMS", "ALIYUN_SMS", "JPUSH", "MESSAGE_BOX", "MAIL"}, message = "网关码错误")
    private String gatewayCode;
    /**
     * 网关账号
     */
    @NotBlank(message = "网关账号不得为空")
    private String gatewayAccount;
    /**
     * 网关密码
     */
    @NotBlank(message = "网关密码不得为空")
    private String gatewayPassword;
    /**
     * 网关签名
     */
    private String gatewaySign;
    /**
     * 登录用户
     */
    @NotNull(message = "登录用户不得为空")
    private Long loginUserId;

}
