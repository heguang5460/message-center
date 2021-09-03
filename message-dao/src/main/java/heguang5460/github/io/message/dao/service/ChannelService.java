package heguang5460.github.io.message.dao.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import heguang5460.github.io.message.dao.domain.db.Channel;
import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import heguang5460.github.io.message.dao.mapper.ChannelMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 渠道表 服务实现类
 * </p>
 *
 * @author he guang
 * @since 2021-08-30
 */
@Slf4j
@Service
public class ChannelService extends ServiceImpl<ChannelMapper, Channel> implements IService<Channel> {

    /**
     * 通过渠道码查询
     *
     * @param channelCode
     * @return
     */
    public Channel queryByChannelCode(ChannelCodeEnum channelCode) {
        if (Objects.isNull(channelCode)) {
            log.warn("ChannelService.queryByChannelCode参数为null,直接返回null");
            return null;
        }
        Channel entity = this.lambdaQuery()
                .eq(Channel::getChannelCode, channelCode)
                .eq(Channel::getDeleteStatus, DeleteStatusEnum.NOTE_DELETED)
                .one();
        if (Objects.isNull(entity)) {
            log.warn("ChannelService.queryByChannelCode查询结果为null,直接返回null");
        }
        return entity;
    }

    /**
     * 保存渠道实体
     *
     * @param channelCode
     * @param loginUserId
     */
    public void buildChannelEntity(ChannelCodeEnum channelCode, Long loginUserId) {
        Channel entity = new Channel();
        entity.setId(IdWorker.getId());
        entity.setChannelCode(channelCode);
        entity.setDeleteStatus(DeleteStatusEnum.NOTE_DELETED);
        entity.setCreateTime(LocalDateTime.now());
        entity.setCreateBy(loginUserId);
        this.save(entity);
    }

    /**
     * 列表所有消息渠道
     * 按创建时间逆序
     *
     * @return
     */
    public List<Channel> listAllChannel() {
        return this.lambdaQuery()
                .orderByDesc(Channel::getCreateTime)
                .list();
    }
}
