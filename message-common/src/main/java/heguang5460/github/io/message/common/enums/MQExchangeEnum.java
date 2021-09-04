package heguang5460.github.io.message.common.enums;

import java.util.Arrays;
import lombok.Getter;

/**
 * MQ交换机枚举
 * EXCHANGE_SMS
 * EXCHANGE_APP_PUSH
 * EXCHANGE_MESSAGE_BOX
 * EXCHANGE_MAIL
 *
 * @author he guang
 */
@Getter
public enum MQExchangeEnum {

    EXCHANGE_ALL("ALL", "所有渠道交换机"),
    EXCHANGE_SMS("SMS", "手机短信渠道交换机"),
    EXCHANGE_APP_PUSH("APP_PUSH", "APP推送渠道交换机"),
    EXCHANGE_MESSAGE_BOX("MESSAGE_BOX", "留言信箱渠道交换机"),
    EXCHANGE_MAIL("MAIL", "邮件渠道交换机");

    private String channelCode;

    private String description;

    MQExchangeEnum(String channelCode, String description) {
        this.channelCode = channelCode;
        this.description = description;
    }

    public static MQExchangeEnum find(String channelCode) {
        return Arrays.stream(MQExchangeEnum.values())
                .filter(o -> o.getChannelCode().equals(channelCode))
                .findFirst()
                .orElse(null);
    }

}
