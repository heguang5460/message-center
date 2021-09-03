package heguang5460.github.io.message.web.request;

import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/9/3 11:30
 */
@Data
public class SendMessageRequest {

    /**
     * 消息接收者
     */
    @NotNull(message = "消息接收者不得为空")
    private Long toUser;

    /**
     * 场景码
     */
    @NotBlank(message = "场景码不得为空")
    private String sceneCode;

    /**
     * 参数变量
     */
    private Map<String, String> paramMap;

}
