package heguang5460.github.io.message.core.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * MQ消息 生产者
 *
 * @author he guang
 */
@Slf4j
@Component
public class MQProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private final RabbitTemplate rabbitTemplate;

    public MQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 推数据到MQ
     *
     * @param exchange
     * @param routeKey
     * @param object
     */
    public void produce(String exchange, String routeKey, Object object) {
        rabbitTemplate.convertAndSend(exchange, routeKey, object);
    }

    /**
     * 生产者回调-交换机端
     *
     * @param correlationData
     * @param ack
     * @param cause
     * @author heguang
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            log.error("MQProducer produce to exchange failed, cause by {}", cause);
        } else {

        }
    }

    /**
     * 生产者回调-队列端
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

    }
}
