package heguang5460.github.io.message.dao.domain.db;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 场景表
 * </p>
 *
 * @author he guang
 * @since 2021-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Scene extends Model<Scene> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 场景码
     */
    private String sceneCode;

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
