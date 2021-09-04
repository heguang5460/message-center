package heguang5460.github.io.message.core.router.channel;

import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * 渠道路由器的抽象类
 *
 * @author heguang
 */
@Slf4j
public abstract class AbstractChannelRouter implements ChannelRouter {

    /**
     * 消息类型
     *
     * @return
     * @author heguang
     */
    protected abstract ChannelCodeEnum channelCode();

    /**
     * 初始化存储对象
     *
     * @author heguang
     */
    @PostConstruct
    protected void init() {
        ChannelRouterFactory.registry(channelCode(), this);
    }

}
