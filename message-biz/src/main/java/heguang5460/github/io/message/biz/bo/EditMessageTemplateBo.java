package heguang5460.github.io.message.biz.bo;

import lombok.Builder;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/9/1 14:00
 */
@Data
@Builder
public class EditMessageTemplateBo {

    /**
     * 网关码
     */
    private String templateCode;

    /**
     * 模板内容
     */
    private String templateContent;

    /**
     * 当前登录用户
     */
    private Long loginUserId;
}
