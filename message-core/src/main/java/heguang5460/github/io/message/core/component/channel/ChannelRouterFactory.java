package heguang5460.github.io.message.core.component.channel;

import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 渠道路由器工厂类
 *
 * @author he guang
 */
public class ChannelRouterFactory {

    private static final Map<ChannelCodeEnum, ChannelRouter> beanMap = new ConcurrentHashMap<>();

    /**
     * 按类型注册路由器
     *
     * @param channelCode
     * @param channelRouter
     */
    public static void registry(ChannelCodeEnum channelCode, ChannelRouter channelRouter) {
        if (!beanMap.containsKey(channelRouter)) {
            beanMap.put(channelCode, channelRouter);
        }
    }

    /**
     * 通过类型获取对应的路由器
     *
     * @param channelCode
     * @return
     */
    public static ChannelRouter newInstance(ChannelCodeEnum channelCode) {
        return beanMap.get(channelCode);
    }
}
