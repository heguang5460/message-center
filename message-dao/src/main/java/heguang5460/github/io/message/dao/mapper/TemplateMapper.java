package heguang5460.github.io.message.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import heguang5460.github.io.message.dao.domain.db.Template;
import heguang5460.github.io.message.dao.domain.vo.PageMessageTemplateVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 模板表 Mapper 接口
 * </p>
 *
 * @author he guang
 * @since 2021-08-30
 */
public interface TemplateMapper extends BaseMapper<Template> {

    /**
     * 联表查询分页信息
     * 关联查询场景表、渠道表、通道表、模板表
     *
     * @param page
     * @param sceneCode
     * @param channelCode
     * @param gatewayCode
     * @return
     */
    Page<PageMessageTemplateVo> queryPagination(
            @Param("page") Page<PageMessageTemplateVo> page,
            @Param("sceneCode") String sceneCode,
            @Param("channelCode") String channelCode,
            @Param("gatewayCode") String gatewayCode);
}
