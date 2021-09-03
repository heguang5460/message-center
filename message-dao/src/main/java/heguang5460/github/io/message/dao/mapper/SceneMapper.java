package heguang5460.github.io.message.dao.mapper;

import heguang5460.github.io.message.dao.domain.db.Scene;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 场景表 Mapper 接口
 * </p>
 *
 * @author he guang
 * @since 2021-08-30
 */
public interface SceneMapper extends BaseMapper<Scene> {

    /**
     * 插入中间表scene_channel_relation
     * 维护场景和渠道的关系
     * @param sceneId
     * @param channelId
     */
    void bindRelation(@Param("sceneId") Long sceneId, @Param("channelId") Long channelId);
}
