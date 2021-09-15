package heguang5460.github.io.message.dao.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;

/**
 * 网关码枚举
 *
 * @author he guang
 */
@Getter
public enum GatewayCodeEnum {

    MWKJ_SMS("MWKJ_SMS", "梦网科技短信"),
    ALIYUN_SMS("ALIYUN_SMS", "阿里云短信"),

    JPUSH("JPUSH", "极光推送"),

    MESSAGE_BOX("MESSAGE_BOX", "留言信箱"),

    MAIL("MAIL", "邮件");

    @EnumValue
    @JsonValue
    private String code;

    private String description;

    GatewayCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonCreator
    public static GatewayCodeEnum find(String gatewayCode) {
        return Arrays.stream(GatewayCodeEnum.values())
                .filter(o -> o.getCode().equals(gatewayCode))
                .findFirst()
                .orElse(null);
    }

}
