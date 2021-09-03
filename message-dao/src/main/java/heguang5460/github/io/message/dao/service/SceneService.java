package heguang5460.github.io.message.dao.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import heguang5460.github.io.message.dao.domain.db.Scene;
import heguang5460.github.io.message.dao.domain.db.Template;
import heguang5460.github.io.message.dao.mapper.SceneMapper;
import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 场景表 服务实现类
 * </p>
 *
 * @author he guang
 * @since 2021-08-30
 */
@Slf4j
@Service
public class SceneService extends ServiceImpl<SceneMapper, Scene> implements IService<Scene> {

    /**
     * 配置场景
     * 并且维护场景和渠道的关系
     *
     * @param sceneCode
     * @param channelId
     * @param loginUserId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Scene saveSceneAndBindRelation(String sceneCode, Long channelId, Long loginUserId) {
        Scene scene = this.buildSceneEntity(sceneCode, loginUserId);
        this.bindRelation(scene.getId(), channelId);
        return scene;
    }

    /**
     * 维护场景渠道关系表
     * 使用REPLACE INTO
     * @param sceneId
     * @param channelId
     */
    public void bindRelation(Long sceneId, Long channelId) {
        this.baseMapper.bindRelation(sceneId, channelId);
    }

    /**
     * 构建场景实体
     * 并且保存
     *
     * @param sceneCode 存数据库大写
     * @param loginUserId
     * @return
     */
    private Scene buildSceneEntity(String sceneCode, Long loginUserId) {
        Scene scene = new Scene();
        scene.setId(IdWorker.getId());
        scene.setSceneCode(sceneCode.toUpperCase());
        scene.setDeleteStatus(DeleteStatusEnum.NOTE_DELETED);
        scene.setCreateTime(LocalDateTime.now());
        scene.setCreateBy(loginUserId);
        this.save(scene);
        return scene;
    }

    /**
     * 根据场景码查询场景配置
     * @param sceneCode
     * @return
     */
    public Scene queryBySceneCode(String sceneCode) {
        if (StringUtils.isEmpty(sceneCode)) {
            log.warn("SceneService.queryBySceneCode参数为空，直接返回null");
            return null;
        }
        Scene entity = this.lambdaQuery()
                .eq(Scene::getSceneCode, sceneCode)
                .one();
        if (Objects.isNull(entity)) {
            log.warn("SceneService.queryBySceneCode查询结果为null，直接返回null");
        }
        return entity;
    }
}
