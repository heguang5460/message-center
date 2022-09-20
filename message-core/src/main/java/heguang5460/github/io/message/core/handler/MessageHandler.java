package heguang5460.github.io.message.core.handler;

import heguang5460.github.io.message.common.util.TemplateUtil;
import heguang5460.github.io.message.core.component.channel.ChannelRouterFactory;
import heguang5460.github.io.message.core.ex.CoreException;
import heguang5460.github.io.message.core.model.MessageModel;
import heguang5460.github.io.message.core.model.SceneChannelMQModel;
import heguang5460.github.io.message.dao.domain.db.Gateway;
import heguang5460.github.io.message.dao.domain.db.Log;
import heguang5460.github.io.message.dao.domain.db.Template;
import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import heguang5460.github.io.message.dao.service.GatewayService;
import heguang5460.github.io.message.dao.service.LogService;
import heguang5460.github.io.message.dao.service.TaskService;
import heguang5460.github.io.message.dao.service.TemplateService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 消息处理器
 *
 * @author he guang
 * @date 2021/9/4 17:57
 */
@Slf4j
@Component
public class MessageHandler {

    @Autowired
    private LogService logService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private TemplateService templateService;

    @Transactional(rollbackFor = Exception.class)
    public void handlerMqModel(SceneChannelMQModel data) {
        // 领取任务
        taskService.removeById(data.getTaskId());
        // 查询网关
        Gateway gateway = gatewayService.queryByChannelId(data.getChannelId());
        if (Objects.isNull(gateway)) {
            throw new CoreException("MQConsumer.consume查询网关配置为空");
        }
        // 查询模板
        Template template = templateService.queryBySceneChannelGateway(
                data.getSceneId(), data.getChannelId(), gateway.getId());
        if (Objects.isNull(template)) {
            throw new CoreException("MQConsumer.consume查询模板配置为空");
        }
        // 合成消息文本
        String messageContent = TemplateUtil.templateContentToMessageContent(
                data.getParamMap(), template.getTemplateContent());
        // 记录流水
        Log logEntity = logService.buildLogEntity(
                data.getSceneId(),
                data.getChannelId(),
                gateway.getId(),
                template.getId(),
                messageContent
        );
        // 组合参数
        MessageModel messageModel = new MessageModel();
        messageModel.setGateway(gateway);
        messageModel.setTemplate(template);
        messageModel.setLog(logEntity);
        messageModel.setFromUser(data.getFromUser());
        messageModel.setToUser(data.getToUser());
        messageModel.setParamMap(data.getParamMap());
        messageModel.setMessageContent(messageContent);
        // 渠道路由
        ChannelRouterFactory.newInstance(ChannelCodeEnum.find(data.getChannelCode())).route(messageModel);
    }

}
