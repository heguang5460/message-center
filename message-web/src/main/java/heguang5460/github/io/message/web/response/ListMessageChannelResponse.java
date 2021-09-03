package heguang5460.github.io.message.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/8/31 14:29
 */
@Data
public class ListMessageChannelResponse {

    /**
     * 渠道码
     */
    private ChannelCodeEnum channelCode;

    /**
     * 删除标识 0未被删除 1已被删除
     */
    private DeleteStatusEnum deleteStatus;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    private Long updateBy;

}
