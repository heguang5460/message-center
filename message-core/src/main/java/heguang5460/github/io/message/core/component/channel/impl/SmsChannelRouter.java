package heguang5460.github.io.message.core.component.channel.impl;

import cn.hutool.json.JSONUtil;
import heguang5460.github.io.message.core.component.channel.AbstractChannelRouter;
import heguang5460.github.io.message.core.model.MessageModel;
import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 手机短信sms渠道路由器
 *
 * @author he guang
 */
@Slf4j
@Service
public class SmsChannelRouter extends AbstractChannelRouter {

    @Override
    protected ChannelCodeEnum channelCode() {
        return ChannelCodeEnum.SMS;
    }

    @Override
    public void route(MessageModel messageModel) {
        log.info("路由到短信渠道：messageModel={}", JSONUtil.toJsonStr(messageModel));
        super.route(messageModel);
    }
}
