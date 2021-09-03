package heguang5460.github.io.message.biz.bo;

import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/8/31 14:15
 */
@Data
@Builder
public class SaveMessageChannelBo {

    /**
     * 渠道码
     */
    private ChannelCodeEnum channelCode;

    /**
     * 登录用户
     */
    private Long loginUserId;

}
