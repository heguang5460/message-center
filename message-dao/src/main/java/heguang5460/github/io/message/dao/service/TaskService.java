package heguang5460.github.io.message.dao.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import heguang5460.github.io.message.dao.domain.db.Task;
import heguang5460.github.io.message.dao.mapper.TaskMapper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务表 服务实现类
 * </p>
 *
 * @author he guang
 * @since 2021-09-03
 */
@Service
public class TaskService extends ServiceImpl<TaskMapper, Task> implements IService<Task> {

    /**
     * 构建任务实体
     *
     * @param sceneId
     * @param channelId
     * @param jsonStr
     */
    public Task buildTaskEntity(Long sceneId, Long channelId, String jsonStr) {
        Task task = new Task();
        task.setId(IdWorker.getId());
        task.setSceneId(sceneId);
        task.setChannelId(channelId);
        task.setParamJson(jsonStr);
        task.setCreateTime(LocalDateTime.now());
        this.save(task);
        return task;
    }
}
