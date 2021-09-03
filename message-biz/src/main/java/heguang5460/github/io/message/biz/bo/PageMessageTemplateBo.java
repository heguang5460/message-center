package heguang5460.github.io.message.biz.bo;

import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/8/30 20:57
 */
@Data
@Builder
public class PageMessageTemplateBo {

    /**
     * 场景ID
     */
    private String sceneCode;

    /**
     * 渠道ID
     */
    private String channelCode;

    /**
     * 网关ID
     */
    private String gatewayCode;

    /**
     * 网关签名
     */
    private String gatewaySign;

    /**
     * 模板编号
     */
    private String templateCode;

    /**
     * 模板内容
     */
    private String templateContent;

    /**
     * 删除标识
     */
    private DeleteStatusEnum deleteStatus;

    /**
     * 模板创建时间
     */
    private LocalDateTime createTime;

    /**
     * 模板创建人
     */
    private Long createBy;

    /**
     * 模板更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 模板更新人
     */
    private Long updateBy;

}
