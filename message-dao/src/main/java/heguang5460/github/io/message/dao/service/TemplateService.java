package heguang5460.github.io.message.dao.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import heguang5460.github.io.message.dao.domain.db.Template;
import heguang5460.github.io.message.dao.domain.vo.PageMessageTemplateVo;
import heguang5460.github.io.message.dao.enums.DeleteStatusEnum;
import heguang5460.github.io.message.dao.mapper.TemplateMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 模板表 服务实现类
 * </p>
 *
 * @author he guang
 * @since 2021-08-30
 */
@Service
public class TemplateService extends ServiceImpl<TemplateMapper, Template> implements
        IService<Template> {

    /**
     * 生成模板码
     *
     * @param sceneCode   大写
     * @param channelCode
     * @param gatewayCode 大写
     * @return
     */
    public String generateTemplateCode(String sceneCode, String channelCode, String gatewayCode) {
        StringBuilder sb = new StringBuilder();
        String templateCode = sb.append(channelCode)
                .append("_")
                .append(sceneCode.toUpperCase())
                .append("_")
                .append(gatewayCode.toUpperCase())
                .toString();
        return templateCode;
    }

    /**
     * 构建模板实体
     * 并且入库保存
     *
     * @param sceneId
     * @param channelId
     * @param gatewayId
     * @param templateCode    大写
     * @param templateContent
     * @param loginUserId
     * @return
     */
    public Template buildTemplateEntity(
            Long sceneId, Long channelId, Long gatewayId,
            String templateCode, String templateContent,
            Long loginUserId) {
        Template template = new Template();
        template.setId(IdWorker.getId());
        template.setSceneId(sceneId);
        template.setChannelId(channelId);
        template.setGatewayId(gatewayId);
        template.setTemplateCode(templateCode);
        template.setTemplateContent(templateContent);
        template.setDeleteStatus(DeleteStatusEnum.NOTE_DELETED);
        template.setCreateTime(LocalDateTime.now());
        template.setCreateBy(loginUserId);
        this.save(template);
        return template;
    }

    /**
     * 消息配置分页列表查询
     * 关联场景表、渠道表、通道表、模板表
     *
     * @param current
     * @param size
     * @param sceneCode
     * @param channelCode
     * @param gatewayCode
     * @return
     */
    public Page<PageMessageTemplateVo> pageMessageConfig(
            long current, long size,
            String sceneCode, String channelCode, String gatewayCode) {
        Page<PageMessageTemplateVo> page = this.baseMapper.queryPagination(
                new Page<PageMessageTemplateVo>(current, size),
                sceneCode,
                channelCode,
                gatewayCode
        );
        return page;
    }

    /**
     * 通过模板码查询模板
     *
     * @param templateCode
     * @return
     */
    public Template queryByTemplateCode(String templateCode) {
        if (StringUtils.isEmpty(templateCode)) {
            log.warn("TemplateService.queryByTemplateCode参数为空，直接返回null");
            return null;
        }
        Template entity = this.lambdaQuery()
                .eq(Template::getTemplateCode, templateCode)
                .one();
        if (Objects.isNull(entity)) {
            log.warn("TemplateService.queryByTemplateCode查询结果为null，直接返回null");
        }
        return entity;
    }

    /**
     * 查询模板
     *
     * @param sceneId
     * @param channelId
     * @param gatewayId
     * @return
     */
    public Template queryBySceneChannelGateway(Long sceneId, Long channelId, Long gatewayId) {
        Template entity = this.lambdaQuery()
                .eq(Template::getSceneId, sceneId)
                .eq(Template::getChannelId, channelId)
                .eq(Template::getGatewayId, gatewayId)
                .one();
        if (Objects.isNull(entity)) {
            log.warn("TemplateService.queryBySceneChannelGateway查询结果为空，返回null");
        }
        return entity;
    }
}
