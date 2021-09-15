package heguang5460.github.io.message.biz.bo;

import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import heguang5460.github.io.message.dao.enums.GatewayCodeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/8/30 20:42
 */
@Data
@Builder
public class SaveMessageTemplateBo {

    private String sceneCode;

    private ChannelCodeEnum channelCode;

    private GatewayCodeEnum gatewayCode;

    private String templateContent;

    private Long loginUserId;

}
