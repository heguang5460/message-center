package heguang5460.github.io.message.dao.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import heguang5460.github.io.message.dao.domain.db.Log;
import heguang5460.github.io.message.dao.enums.MessageStatusEnum;
import heguang5460.github.io.message.dao.ex.DaoException;
import heguang5460.github.io.message.dao.mapper.LogMapper;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author he guang
 * @since 2021-09-03
 */
@Slf4j
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

    /**
     * 更新消息的日志状态
     *
     * @param messageStatusEnum
     * @param localDateTime
     * @param entity
     */
    public void updateMessageStatus(MessageStatusEnum messageStatusEnum, LocalDateTime localDateTime, Log entity) {
        if (Objects.isNull(messageStatusEnum)) {
            log.error("LogService.updateMessageStatus参数messageStatusEnum为null，直接返回");
            return;
        }
        switch (messageStatusEnum) {
            case SENDING:
                entity.setMessageStatus(MessageStatusEnum.SENDING);
                entity.setSendingTime(localDateTime);
                break;
            case SEND_SUCCESS:
                entity.setMessageStatus(MessageStatusEnum.SEND_SUCCESS);
                entity.setSentTime(localDateTime);
                break;
            case SEND_FAIL:
                entity.setMessageStatus(MessageStatusEnum.SEND_FAIL);
                entity.setSentTime(localDateTime);
                break;
            case RECEIVE_SUCCESS:
                entity.setMessageStatus(MessageStatusEnum.RECEIVE_SUCCESS);
                entity.setReceiveTime(localDateTime);
                break;
            case RECEIVE_FAIL:
                entity.setMessageStatus(MessageStatusEnum.RECEIVE_FAIL);
                entity.setReceiveTime(localDateTime);
                break;
            default:
                log.error("LogService.updateMessageStatus参数messageStatusEnum={}不支持类型", messageStatusEnum);
                throw new DaoException("LogService.updateMessageStatus参数messageStatusEnum不支持类型");
        }
        this.updateById(entity);
    }
}
