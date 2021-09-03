package heguang5460.github.io.message.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author he guang
 * @date 2021/9/1 12:23
 */
@Data
public class EditMessageGatewayRequest {

    /**
     * 旧消息网关码
     */
    @NotBlank(message = "旧网关码不得为空")
    @Length(max = 32, message = "旧网关码最大长度为32个字符")
    private String oldGatewayCode;
    /**
     * 新消息网关码
     */
    private String newGatewayCode;
    /**
     * 网关账号
     */
    private String gatewayAccount;
    /**
     * 网关密码
     */
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
