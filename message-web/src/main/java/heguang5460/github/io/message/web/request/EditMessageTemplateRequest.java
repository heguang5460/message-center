package heguang5460.github.io.message.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author he guang
 * @date 2021/9/1 14:00
 */
@Data
public class EditMessageTemplateRequest {

    /**
     * 网关码
     */
    @NotBlank(message = "模板码不得为空")
    @Length(max = 32, message = "模板码最大长度为32个字符")
    private String templateCode;

    /**
     * 模板内容
     */
    @NotBlank(message = "模板内容不得为空")
    private String templateContent;

    /**
     * 当前登录用户
     */
    @NotNull(message = "登录用户不得为空")
    private Long loginUserId;

}
