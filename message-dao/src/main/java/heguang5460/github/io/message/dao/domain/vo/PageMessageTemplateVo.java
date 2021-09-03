package heguang5460.github.io.message.dao.domain.vo;

import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/8/31 11:26
 */
@Data
public class PageMessageTemplateVo {

    private String sceneCode;

    private String channelCode;

    private String gatewayCode;

    private String gatewayAccount;

    private String gatewayPassword;

    private String gatewaySign;

    private String templateCode;

    private String templateContent;

    private DeleteStatusEnum deleteStatus;

    private LocalDateTime createTime;

    private Long createBy;

    private LocalDateTime updateTime;

    private Long updateBy;

}
