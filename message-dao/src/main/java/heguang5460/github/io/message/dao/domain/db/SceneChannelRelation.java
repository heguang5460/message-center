package heguang5460.github.io.message.dao.domain.db;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 场景渠道关系表
 * </p>
 *
 * @author he guang
 * @since 2021-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SceneChannelRelation extends Model<SceneChannelRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 场景ID
     */
    private Long sceneId;

    /**
     * 渠道ID
     */
    private Long channelId;


    @Override
    protected Serializable pkVal() {
        return this.sceneId;
    }

}
