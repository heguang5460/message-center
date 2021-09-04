package heguang5460.github.io.message.core.router.channel.impl;

import heguang5460.github.io.message.core.model.MessageModel;
import heguang5460.github.io.message.core.router.channel.AbstractChannelRouter;
import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 事件类型消息路由器
 *
 * @author he guang
 */
@Service
@Slf4j
public class MailChannelRouter extends AbstractChannelRouter {

    @Override
    protected ChannelCodeEnum channelCode() {
        return ChannelCodeEnum.MAIL;
    }

    @Override
    public void route(MessageModel messageModel) {
    }
}
