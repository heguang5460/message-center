package heguang5460.github.io.message.dao.mapper;

import heguang5460.github.io.message.dao.domain.db.Gateway;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import heguang5460.github.io.message.dao.domain.vo.GatewayVo;
import java.util.List;

/**
 * <p>
 * 网关表 Mapper 接口
 * </p>
 *
 * @author he guang
 * @since 2021-08-30
 */
public interface GatewayMapper extends BaseMapper<Gateway> {

    List<GatewayVo> listAllGateway();
}
