package heguang5460.github.io.message.web.request;

import heguang5460.github.io.message.common.result.BasePage;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/8/30 20:55
 */
@Data
public class PageMessageTemplateRequest extends BasePage {

    /**
     * 场景码
     */
    private String sceneCode;

    /**
     * 渠道码
     */
    private String channelCode;

    /**
     * 网关码
     */
    private String gatewayCode;

}
