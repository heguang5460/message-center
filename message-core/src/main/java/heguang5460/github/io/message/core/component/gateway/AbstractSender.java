package heguang5460.github.io.message.core.component.gateway;

import heguang5460.github.io.message.core.model.MessageModel;
import heguang5460.github.io.message.dao.domain.db.Log;
import heguang5460.github.io.message.dao.enums.GatewayCodeEnum;
import heguang5460.github.io.message.dao.enums.MessageStatusEnum;
import heguang5460.github.io.message.dao.service.LogService;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author he guang
 * @date 2021/9/15 15:30
 */
@Slf4j
public abstract class AbstractSender implements Sender {

    @Autowired
    protected LogService logService;

    /**
     * 各子类实例化之后调用
     */
    @PostConstruct
    protected void init() {
        GatewaySenderFactory.registry(this.gatewayCode(), this);
    }

    /**
     * 发送消息
     * 模板方法逻辑
     *
     * @param messageModel
     */
    @Override
    public void send(MessageModel messageModel) {
        //记录此消息为发送中
        this.updateMessageStatus(MessageStatusEnum.SENDING, LocalDateTime.now(), messageModel.getLog());
        //调用三方
        try {
            realSend(messageModel);
        } catch (Exception e) {
            //记录发送完成状态-发送失败
            log.error("网关子类GatewayCodeEnum={}发送失败，失败异常信息ex={}", gatewayCode(), e.getMessage());
            this.updateMessageStatus(MessageStatusEnum.SEND_FAIL, LocalDateTime.now(), messageModel.getLog());
            return;
        }
        //记录发送完成状态-发送成功
        this.updateMessageStatus(MessageStatusEnum.SEND_SUCCESS, LocalDateTime.now(), messageModel.getLog());
    }

    /**
     * 子类定义自己的类型
     *
     * @return
     */
    protected abstract GatewayCodeEnum gatewayCode();

    /**
     * 真实发送逻辑，对接三方
     * 交给子类实现
     */
    protected abstract void realSend(MessageModel messageModel);

    /**
     * 记录消息的日志状态流水
     *
     * @param messageStatusEnum
     * @param localDateTime
     * @param entity
     */
    protected void updateMessageStatus(MessageStatusEnum messageStatusEnum, LocalDateTime localDateTime, Log entity) {
        logService.updateMessageStatus(messageStatusEnum, localDateTime, entity);
    }

}
