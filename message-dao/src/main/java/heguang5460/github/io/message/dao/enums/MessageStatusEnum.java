package heguang5460.github.io.message.dao.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;

/**
 * 消息状态枚举
 * 消息状态 1待发送 2发送中 3发送成功 4发送失败 5接收成功 6接收失败
 *
 * @author he guang
 */
@Getter
public enum MessageStatusEnum {

    TO_BE_SEND(1, "待发送"),
    SENDING(2, "发送中"),
    SEND_SUCCESS(3, "发送成功"),
    SEND_FAIL(4, "发送失败"),
    RECEIVE_SUCCESS(5, "接收成功"),
    RECEIVE_FAIL(6, "接收失败");

    @EnumValue
    @JsonValue
    private Integer code;

    private String description;

    MessageStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonCreator
    public static MessageStatusEnum find(Integer code) {
        return Arrays.stream(MessageStatusEnum.values())
                .filter(o -> o.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
