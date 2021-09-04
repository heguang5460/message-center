package heguang5460.github.io.message.biz.bo;

import com.sun.istack.internal.NotNull;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/9/3 11:30
 */
@Data
@Builder
public class SendMessageBo {


    /**
     * 消息发送者
     */
    private Long fromUser;
    /**
     * 消息接收者
     */
    private Long toUser;

    /**
     * 场景码
     */
    private String sceneCode;

    /**
     * 参数变量
     */
    private Map<String, String> paramMap;

}
