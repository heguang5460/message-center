package heguang5460.github.io.message.core.component.channel.impl;

import cn.hutool.json.JSONUtil;
import heguang5460.github.io.message.core.model.MessageModel;
import heguang5460.github.io.message.core.component.channel.AbstractChannelRouter;
import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 留言信箱渠道路由器
 *
 * @author he guang
 */
@Slf4j
@Service
public class MessageBoxChannelRouter extends AbstractChannelRouter {

    @Override
    protected ChannelCodeEnum channelCode() {
        return ChannelCodeEnum.MESSAGE_BOX;
    }

    @Override
    public void route(MessageModel messageModel) {
        log.info("===================MESSAGE_BOX===================");
        log.info("messageModel={}", JSONUtil.toJsonStr(messageModel));
    }
}
