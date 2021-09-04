package heguang5460.github.io.message.core.mq.consumer;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import heguang5460.github.io.message.common.constants.TopicConstants;
import heguang5460.github.io.message.common.util.TemplateUtil;
import heguang5460.github.io.message.core.ex.CoreException;
import heguang5460.github.io.message.core.model.MessageModel;
import heguang5460.github.io.message.core.model.SceneChannelMQModel;
import heguang5460.github.io.message.core.router.channel.ChannelRouterFactory;
import heguang5460.github.io.message.dao.domain.db.Gateway;
import heguang5460.github.io.message.dao.domain.db.Log;
import heguang5460.github.io.message.dao.domain.db.Template;
import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import heguang5460.github.io.message.dao.service.GatewayService;
import heguang5460.github.io.message.dao.service.LogService;
import heguang5460.github.io.message.dao.service.TaskService;
import heguang5460.github.io.message.dao.service.TemplateService;
import java.io.IOException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author he guang
 */
@Slf4j
@Component
@RabbitListener(queues = TopicConstants.TOPIC_MESSAGE_TASK)
public class MQConsumer {

    @Autowired
    private LogService logService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private TemplateService templateService;

    @RabbitHandler
    public void consume(SceneChannelMQModel data, Message message, Channel channel) throws IOException {
        log.info("MQConsumer.consume接收到SceneChannelMQModel={}", JSONUtil.toJsonStr(data));

        boolean isSuccess = false;
        try {
            this.handlerMqModel(data);
            isSuccess = true;
        } catch (Exception e) {
            log.error("MQConsumer.consume消费失败，失败原因Exception={}", e.getMessage());
        }

        //手动提交mq
        if (isSuccess) {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } else {
            if (message.getMessageProperties().getRedelivered()) {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void handlerMqModel(SceneChannelMQModel data) {
        //领取任务
        taskService.removeById(data.getTaskId());
        //查询网关
        Gateway gateway = gatewayService.queryByChannelId(data.getChannelId());
        if (Objects.isNull(gateway)) {
            throw new CoreException("MQConsumer.consume查询网关配置为空");
        }
        //查询模板
        Template template = templateService.queryBySceneChannelGateway(
                data.getSceneId(), data.getChannelId(), gateway.getId());
        if (Objects.isNull(template)) {
            throw new CoreException("MQConsumer.consume查询模板配置为空");
        }
        //合成消息文本
        String messageContent = TemplateUtil.templateContentToMessageContent(
                data.getParamMap(), template.getTemplateCode());
        //记录流水
        Log logEntity = logService.buildLogEntity(
                data.getSceneId(),
                data.getChannelId(),
                gateway.getId(),
                template.getId(),
                messageContent
        );
        //组合参数
        MessageModel messageModel = new MessageModel();
        messageModel.setGateway(gateway);
        messageModel.setTemplate(template);
        messageModel.setLog(logEntity);
        messageModel.setFromUser(data.getFromUser());
        messageModel.setToUser(data.getToUser());
        messageModel.setParamMap(data.getParamMap());
        messageModel.setMessageContent(messageContent);
        //渠道路由
        ChannelRouterFactory.newInstance(ChannelCodeEnum.find(data.getChannelCode())).route(messageModel);
    }

}
