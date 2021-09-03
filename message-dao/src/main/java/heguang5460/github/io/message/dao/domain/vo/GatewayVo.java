package heguang5460.github.io.message.dao.domain.vo;

import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/8/31 15:34
 */
@Data
public class GatewayVo {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 渠道渠道码
     */
    private String channelCode;

    /**
     * 网关码
     */
    private String gatewayCode;

    /**
     * 账号
     */
    private String gatewayAccount;

    /**
     * 密码
     */
    private String gatewayPassword;

    /**
     * 签名
     */
    private String gatewaySign;

    /**
     * 删除标识 0未被删除 1已被删除
     */
    private DeleteStatusEnum deleteStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    private Long updateBy;

}
