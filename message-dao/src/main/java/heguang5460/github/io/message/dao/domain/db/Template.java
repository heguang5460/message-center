package heguang5460.github.io.message.dao.domain.db;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 模板表
 * </p>
 *
 * @author he guang
 * @since 2021-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Template extends Model<Template> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 场景ID
     */
    private Long sceneId;

    /**
     * 渠道ID
     */
    private Long channelId;

    /**
     * 网关ID
     */
    private Long gatewayId;

    /**
     * 模板码
     */
    private String templateCode;

    /**
     * 模板内容
     */
    private String templateContent;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
