package heguang5460.github.io.message.core.mq.consumer;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import heguang5460.github.io.message.common.constants.TopicConstants;
import heguang5460.github.io.message.core.handler.MessageHandler;
import heguang5460.github.io.message.core.model.SceneChannelMQModel;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author he guang
 */
@Slf4j
@Component
@RabbitListener(queues = TopicConstants.TOPIC_MESSAGE_TASK)
public class MQConsumer {

    @Autowired
    private MessageHandler messageHandler;

    @RabbitHandler
    public void consume(SceneChannelMQModel data, Message message, Channel channel) throws IOException {
        log.info("MQConsumer.consume接收到SceneChannelMQModel={}", JSONUtil.toJsonStr(data));

        boolean isSuccess = false;
        try {
            messageHandler.handlerMqModel(data);
            isSuccess = true;
        } catch (Exception e) {
            log.error("MQConsumer.consume消费失败，失败原因Exception={}", e.getMessage());
        }

        // 手动提交mq
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

}
