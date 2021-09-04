package heguang5460.github.io.message.core.model;

import java.io.Serializable;
import java.util.Map;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/9/3 13:37
 */
@Data
public class SceneChannelMQModel implements Serializable {

    private Long taskId;

    private Long sceneId;

    private String sceneCode;

    private Long channelId;

    private String channelCode;

    private Long fromUser;

    private Long toUser;

    private Map<String, String> paramMap;

}
