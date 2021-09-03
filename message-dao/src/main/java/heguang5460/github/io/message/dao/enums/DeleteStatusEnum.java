package heguang5460.github.io.message.dao.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;

/**
 * 删除标识枚举
 * 是否删除 0未被删除 1已被删除
 *
 * @author he guang
 */
@Getter
public enum DeleteStatusEnum {

    NOTE_DELETED(0, "未被删除"),
    DELETED(1, "已被删除");

    @EnumValue
    @JsonValue
    private Integer code;

    private String description;

    DeleteStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonCreator
    public static DeleteStatusEnum find(Integer code) {
        return Arrays.stream(DeleteStatusEnum.values())
                .filter(o -> o.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
