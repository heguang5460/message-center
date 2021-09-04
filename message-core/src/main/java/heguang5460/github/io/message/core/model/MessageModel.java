package heguang5460.github.io.message.core.model;

import heguang5460.github.io.message.dao.domain.db.Gateway;
import heguang5460.github.io.message.dao.domain.db.Log;
import heguang5460.github.io.message.dao.domain.db.Template;
import java.io.Serializable;
import java.util.Map;
import lombok.Data;

/**
 * @author he guang
 * @date 2021/9/3 13:37
 */
@Data
public class MessageModel implements Serializable {

    //数据库数据配置

    private Gateway gateway;

    private Template template;

    private Log log;

    //消息发送需要参数

    private Long fromUser;

    private Long toUser;

    private Map<String, String> paramMap;

    private String messageContent;

}
