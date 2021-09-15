package heguang5460.github.io.message.core.component.gateway;

import heguang5460.github.io.message.dao.enums.GatewayCodeEnum;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author he guang
 * @date 2021/9/15 15:34
 */
public class GatewaySenderFactory {

    /**
     * 实例缓存
     */
    private static Map<GatewayCodeEnum, Sender> gatewayMap = new ConcurrentHashMap<>();

    /**
     * 注册实例到缓存
     *
     * @param gatewayCode
     * @param sender
     */
    public static void registry(GatewayCodeEnum gatewayCode, Sender sender) {
        if (!gatewayMap.containsKey(gatewayCode)) {
            gatewayMap.put(gatewayCode, sender);
        }
    }

    /**
     * 从缓存获取实例
     *
     * @param gatewayCode
     * @return
     */
    public static Sender newInstance(GatewayCodeEnum gatewayCode) {
        return gatewayMap.get(gatewayCode);
    }

}
