package heguang5460.github.io.message.dao.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;

/**
 * 渠道码枚举
 * SMS
 * APP_PUSH
 * MESSAGE_BOX
 * MAIL
 *
 * @author he guang
 */
@Getter
public enum ChannelCodeEnum {

    SMS("SMS", "手机短信"),
    APP_PUSH("APP_PUSH", "APP推送"),
    MESSAGE_BOX("MESSAGE_BOX", "留言信箱"),
    MAIL("MAIL", "邮件");

    @EnumValue
    @JsonValue
    private String channelCode;

    private String description;

    ChannelCodeEnum(String channelCode, String description) {
        this.channelCode = channelCode;
        this.description = description;
    }

    @JsonCreator
    public static ChannelCodeEnum find(String channelCode) {
        return Arrays.stream(ChannelCodeEnum.values())
                .filter(o -> o.getChannelCode().equals(channelCode))
                .findFirst()
                .orElse(null);
    }

}
