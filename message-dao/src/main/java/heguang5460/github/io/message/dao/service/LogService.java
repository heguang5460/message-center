package heguang5460.github.io.message.dao.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import heguang5460.github.io.message.dao.domain.db.Log;
import heguang5460.github.io.message.dao.enums.MessageStatusEnum;
import heguang5460.github.io.message.dao.mapper.LogMapper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author he guang
 * @since 2021-09-03
 */
@Service
public class LogService extends ServiceImpl<LogMapper, Log> implements IService<Log> {

    /**
     * 构建日志流水
     *
     * @param sceneId
     * @param channelId
     * @param gatewayId
     * @param templateId
     * @param messageContent
     * @return
     */
    public Log buildLogEntity(Long sceneId, Long channelId, Long gatewayId, Long templateId, String messageContent) {
        Log entity = new Log();
        entity.setId(IdWorker.getId());
        entity.setSceneId(sceneId);
        entity.setChannelId(channelId);
        entity.setGatewayId(gatewayId);
        entity.setTemplateId(templateId);
        entity.setMessageContent(messageContent);
        entity.setMessageStatus(MessageStatusEnum.TO_BE_SEND);
        entity.setToBeSendTime(LocalDateTime.now());
        this.save(entity);
        return entity;
    }
}
