package heguang5460.github.io.message.core.router.channel;

import heguang5460.github.io.message.core.model.MessageModel;

/**
 * 渠道路由器
 *
 * @author he guang
 */
public interface ChannelRouter {

    /**
     * 渠道路由逻辑
     *
     * @param messageModel
     */
    void route(MessageModel messageModel);

}
