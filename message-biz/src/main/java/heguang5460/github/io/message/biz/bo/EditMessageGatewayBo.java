package heguang5460.github.io.message.biz.bo;

import lombok.Builder;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/9/1 12:27
 */
@Data
@Builder
public class EditMessageGatewayBo {

    /**
     * 旧消息网关码
     */
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
    private Long loginUserId;

}
