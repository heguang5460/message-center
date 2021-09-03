package heguang5460.github.io.message.dao.domain.db;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author he guang
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Log extends Model<Log> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 场景ID
     */
    private Long sceneId;

    /**
     * 渠道ID
     */
    private Long channelId;

    /**
     * 通道ID
     */
    private Long gatewayId;

    /**
     * 模板ID
     */
    private Long templateId;

    /**
     * 消息文本
     */
    private String messageContent;

    /**
     * 消息状态 1待发送 2发送中 3发送成功 4发送失败 5接收成功 6接收失败
     */
    private Integer messageStatus;

    /**
     * 待发送时间
     */
    private LocalDateTime toBeSendTime;

    /**
     * 发送中时间
     */
    private LocalDateTime sendingTime;

    /**
     * 发送完成时间
     */
    private LocalDateTime sentTime;

    /**
     * 接收完成时间
     */
    private LocalDateTime receiveTime;

    /**
     * 三方消息唯一标识
     */
    private String messageTag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
