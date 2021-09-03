package heguang5460.github.io.message.dao.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import heguang5460.github.io.message.dao.domain.db.Gateway;
import heguang5460.github.io.message.dao.domain.vo.GatewayVo;
import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import heguang5460.github.io.message.dao.mapper.GatewayMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 网关表 服务实现类
 * </p>
 *
 * @author he guang
 * @since 2021-08-30
 */
@Slf4j
@Service
public class GatewayService extends ServiceImpl<GatewayMapper, Gateway> implements IService<Gateway> {

    /**
     * 通过网关码查询网关
     *
     * @param gatewayCode
     * @return
     */
    public Gateway queryByGatewayCode(String gatewayCode) {
        if (StringUtils.isEmpty(gatewayCode)) {
            log.warn("GatewayService.queryByGatewayCode参数为空，直接返回null");
            return null;
        }
        Gateway entity = this.lambdaQuery()
                .eq(Gateway::getGatewayCode, gatewayCode)
                .eq(Gateway::getDeleteStatus, DeleteStatusEnum.NOTE_DELETED)
                .one();
        if (Objects.isNull(entity)) {
            log.warn("GatewayService.queryByGatewayCode查询结果为null，直接返回null");
        }
        return entity;
    }

    /**
     * 构建网关实体
     *
     * @param channelId
     * @param gatewayCode 存数据库都是大写
     * @param gatewayAccount
     * @param gatewayPassword
     * @param gatewaySign
     * @param loginUserId
     */
    public void buildGatewayEntity(
            Long channelId,
            String gatewayCode, String gatewayAccount, String gatewayPassword, String gatewaySign, Long loginUserId) {
        Gateway gateway = new Gateway();
        gateway.setId(IdWorker.getId());
        gateway.setChannelId(channelId);
        gateway.setGatewayCode(gatewayCode.toUpperCase());
        gateway.setGatewayAccount(gatewayAccount);
        gateway.setGatewayPassword(gatewayPassword);
        gateway.setGatewaySign(gatewaySign);
        gateway.setDeleteStatus(DeleteStatusEnum.NOTE_DELETED);
        gateway.setCreateTime(LocalDateTime.now());
        gateway.setCreateBy(loginUserId);
        this.save(gateway);
    }

    /**
     * 列表所有网关
     * 联表查询，关联渠道表
     * 按创建时间逆序
     *
     * @return
     */
    public List<GatewayVo> listAllGateway() {
        return this.baseMapper.listAllGateway();
    }
}
