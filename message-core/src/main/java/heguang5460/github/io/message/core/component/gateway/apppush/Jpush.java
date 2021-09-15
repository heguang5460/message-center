package heguang5460.github.io.message.core.component.gateway.apppush;

import heguang5460.github.io.message.core.component.gateway.AbstractSender;
import heguang5460.github.io.message.core.model.MessageModel;
import heguang5460.github.io.message.dao.enums.GatewayCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @author he guang
 * @date 2021/9/13 15:15
 */

@Component
public class Jpush extends AbstractSender {

    @Override
    protected GatewayCodeEnum gatewayCode() {
        return GatewayCodeEnum.JPUSH;
    }

    @Override
    protected void realSend(MessageModel messageModel) {

    }
}
