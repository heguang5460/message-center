package heguang5460.github.io.message.core.component.gateway.messagebox;

import heguang5460.github.io.message.core.component.gateway.AbstractSender;
import heguang5460.github.io.message.core.model.MessageModel;
import heguang5460.github.io.message.dao.enums.GatewayCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @author he guang
 * @date 2021/9/13 15:15
 */

@Component
public class MessageBox extends AbstractSender {

    @Override
    protected GatewayCodeEnum gatewayCode() {
        return GatewayCodeEnum.MESSAGE_BOX;
    }

    @Override
    protected void realSend(MessageModel messageModel) {

    }
}
