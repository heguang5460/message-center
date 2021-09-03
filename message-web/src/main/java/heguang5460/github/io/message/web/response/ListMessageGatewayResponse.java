package heguang5460.github.io.message.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/8/31 15:00
 */
@Data
public class ListMessageGatewayResponse {

    /**
     * 消息渠道
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    private Long updateBy;
}
