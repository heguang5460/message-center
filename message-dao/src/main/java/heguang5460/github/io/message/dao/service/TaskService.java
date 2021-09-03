package heguang5460.github.io.message.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import heguang5460.github.io.message.dao.domain.db.Task;
import heguang5460.github.io.message.dao.mapper.TaskMapper;
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

}
