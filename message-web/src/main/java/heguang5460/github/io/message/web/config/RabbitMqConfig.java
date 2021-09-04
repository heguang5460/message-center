package heguang5460.github.io.message.web.config;

import heguang5460.github.io.message.common.constants.TopicConstants;
import heguang5460.github.io.message.common.enums.MQExchangeEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq 配置
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 任务交换机
     *
     * @author he guang
     */
    @Bean
    public DirectExchange taskExchange() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(MQExchangeEnum.EXCHANGE_TASK.name())
                .durable(true)
                .build();
    }

    /**
     * 任务消息队列
     *
     * @author he guang
     */
    @Bean
    public Queue taskQueue() {
        return new Queue(TopicConstants.TOPIC_MESSAGE_TASK);
    }

    /**
     * 将任务消息队列绑定到任务交换机
     *
     * @author he guang
     */
    @Bean
    public Binding taskQueueBindToTaskExchange(DirectExchange taskExchange, Queue taskQueue) {
        return BindingBuilder
                .bind(taskQueue)
                .to(taskExchange)
                .with(MQExchangeEnum.EXCHANGE_TASK.name());
    }

}
