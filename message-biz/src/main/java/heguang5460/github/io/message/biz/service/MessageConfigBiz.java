package heguang5460.github.io.message.biz.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import heguang5460.github.io.message.biz.bo.EditMessageGatewayBo;
import heguang5460.github.io.message.biz.bo.EditMessageTemplateBo;
import heguang5460.github.io.message.biz.bo.ListMessageChannelBo;
import heguang5460.github.io.message.biz.bo.ListMessageGatewayBo;
import heguang5460.github.io.message.biz.bo.PageMessageTemplateBo;
import heguang5460.github.io.message.biz.bo.SaveMessageChannelBo;
import heguang5460.github.io.message.biz.bo.SaveMessageGatewayBo;
import heguang5460.github.io.message.biz.bo.SaveMessageTemplateBo;
import heguang5460.github.io.message.biz.ex.BizException;
import heguang5460.github.io.message.common.result.CommonPage;
import heguang5460.github.io.message.dao.domain.db.Channel;
import heguang5460.github.io.message.dao.domain.db.Gateway;
import heguang5460.github.io.message.dao.domain.db.Scene;
import heguang5460.github.io.message.dao.domain.db.Template;
import heguang5460.github.io.message.dao.domain.vo.GatewayVo;
import heguang5460.github.io.message.dao.domain.vo.PageMessageTemplateVo;
import heguang5460.github.io.message.dao.service.ChannelService;
import heguang5460.github.io.message.dao.service.GatewayService;
import heguang5460.github.io.message.dao.service.SceneService;
import heguang5460.github.io.message.dao.service.TemplateService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author he guang
 * @date 2021/8/30 20:40
 */
@Slf4j
@Service
public class MessageConfigBiz {

    @Autowired
    private SceneService sceneService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private TemplateService templateService;

    /**
     * 消息渠道配置
     *
     * @param saveMessageChannelBo
     */
    public void saveMessageChannel(SaveMessageChannelBo saveMessageChannelBo) {
        Channel channel = channelService.queryByChannelCode(saveMessageChannelBo.getChannelCode());
        if (Objects.nonNull(channel)) {
            log.error("消息渠道已存在");
            throw new BizException("消息渠道已存在");
        }
        channelService.buildChannelEntity(saveMessageChannelBo.getChannelCode(), saveMessageChannelBo.getLoginUserId());
    }

    /**
     * 消息渠道列表
     *
     * @return
     */
    public List<ListMessageChannelBo> listMessageChannel() {
        List<Channel> channels = channelService.listAllChannel();
        if (CollectionUtil.isNotEmpty(channels)) {
            return channels.stream()
                    .filter(Objects::nonNull)
                    .map(o -> {
                        ListMessageChannelBo bo = new ListMessageChannelBo();
                        BeanUtil.copyProperties(o, bo);
                        return bo;
                    }).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    /**
     * 消息网关配置
     *
     * @param saveMessageGatewayBo
     */
    public void saveMessageGateway(SaveMessageGatewayBo saveMessageGatewayBo) {
        Channel channel = channelService.queryByChannelCode(saveMessageGatewayBo.getChannelCode());
        if (Objects.isNull(channel)) {
            log.error("MessageConfigBiz.saveMessageGateway查询消息渠道为空，channelCode={}",
                    saveMessageGatewayBo.getChannelCode());
            throw new BizException("MessageConfigBiz.saveMessageGateway查询消息渠道为空");
        }
        Gateway gateway = gatewayService.queryByGatewayCode(saveMessageGatewayBo.getGatewayCode());
        if (Objects.nonNull(gateway) && channel.getId().equals(gateway.getChannelId())) {
            log.error("消息网关已存在");
            throw new BizException("消息网关已存在");
        }
        gatewayService.buildGatewayEntity(
                channel.getId(),
                saveMessageGatewayBo.getGatewayCode(),
                saveMessageGatewayBo.getGatewayAccount(),
                saveMessageGatewayBo.getGatewayPassword(),
                saveMessageGatewayBo.getGatewaySign(),
                saveMessageGatewayBo.getLoginUserId()
        );
    }

    /**
     * 编辑修改网关配置
     *
     * @param editMessageGatewayBo
     */
    @Transactional(rollbackFor = Exception.class)
    public void editMessageGateway(EditMessageGatewayBo editMessageGatewayBo) {
        //查询旧配置
        Gateway gateway = gatewayService.queryByGatewayCode(editMessageGatewayBo.getGatewayCode());
        if (Objects.isNull(gateway)) {
            log.error("MessageConfigBiz.editMessageGateway查询网关配置为空，gatewayCode={}",
                    editMessageGatewayBo.getGatewayCode());
            throw new BizException("MessageConfigBiz.editMessageGateway查询网关配置为空");
        }
        //更新旧配置
        if (StrUtil.isNotBlank(editMessageGatewayBo.getGatewayAccount())) {
            gateway.setGatewayAccount(editMessageGatewayBo.getGatewayAccount());
        }
        if (StrUtil.isNotBlank(editMessageGatewayBo.getGatewayPassword())) {
            gateway.setGatewayPassword(editMessageGatewayBo.getGatewayPassword());
        }
        if (StrUtil.isNotBlank(editMessageGatewayBo.getGatewaySign())) {
            gateway.setGatewaySign(editMessageGatewayBo.getGatewaySign());
        }
        gateway.setUpdateBy(editMessageGatewayBo.getLoginUserId());
        gateway.setUpdateTime(LocalDateTime.now());
        gatewayService.updateById(gateway);

    }

    /**
     * 消息网关列表
     *
     * @return
     */
    public List<ListMessageGatewayBo> listMessageGateway() {
        List<GatewayVo> gateways = gatewayService.listAllGateway();
        if (CollectionUtil.isNotEmpty(gateways)) {
            return gateways.stream()
                    .filter(Objects::nonNull)
                    .map(o -> {
                        ListMessageGatewayBo bo = new ListMessageGatewayBo();
                        BeanUtil.copyProperties(o, bo);
                        return bo;
                    }).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    /**
     * 保存消息模板配置
     *
     * @param saveMessageTemplateBo
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveMessageTemplate(SaveMessageTemplateBo saveMessageTemplateBo) {
        //查询渠道配置
        Channel channel = channelService.queryByChannelCode(saveMessageTemplateBo.getChannelCode());
        if (Objects.isNull(channel)) {
            log.error("MessageConfigBiz.saveMessageConfig查询渠道配置为空，channelCode={}",
                    saveMessageTemplateBo.getChannelCode());
            throw new BizException("MessageConfigBiz.saveMessageConfig查询渠道配置为空");
        }
        //查询通道配置
        Gateway gateway = gatewayService.queryByGatewayCode(saveMessageTemplateBo.getGatewayCode());
        if (Objects.isNull(gateway)) {
            log.error("MessageConfigBiz.saveMessageConfig查询网关配置为空，gatewayCode={}",
                    saveMessageTemplateBo.getGatewayCode());
            throw new BizException("MessageConfigBiz.saveMessageConfig查询网关配置为空");
        }
        String templateCode = templateService.generateTemplateCode(
                saveMessageTemplateBo.getSceneCode(),
                channel.getChannelCode().getCode(),
                gateway.getGatewayCode().getCode());
        //查询模板配置
        Template template = templateService.queryByTemplateCode(templateCode);
        if (Objects.nonNull(template)) {
            log.error("消息模板已存在");
            throw new BizException("消息模板已存在");
        }
        //查询场景配置
        Scene scene = sceneService.queryBySceneCode(saveMessageTemplateBo.getSceneCode());
        if (Objects.isNull(scene)) {
            //保存场景、并维护场景和渠道的关系
            scene = sceneService.saveSceneAndBindRelation(
                    saveMessageTemplateBo.getSceneCode(),
                    channel.getId(),
                    saveMessageTemplateBo.getLoginUserId());
        } else {
            //维护场景和渠道的关系
            sceneService.bindRelation(scene.getId(), channel.getId());
        }
        //保存模板
        template = templateService.buildTemplateEntity(
                scene.getId(),
                channel.getId(),
                gateway.getId(),
                templateCode,
                saveMessageTemplateBo.getTemplateContent(),
                saveMessageTemplateBo.getLoginUserId());
        log.info("MessageConfigBiz.saveMessageConfig消息配置成功，场景scene={}, 模板template={}",
                JSONUtil.toJsonStr(scene), JSONUtil.toJsonStr(template));
    }

    /**
     * 编辑修改模板
     *
     * @param editMessageTemplateBo
     */
    public void editMessageTemplate(EditMessageTemplateBo editMessageTemplateBo) {
        Template template = templateService.queryByTemplateCode(editMessageTemplateBo.getTemplateCode());
        if (Objects.isNull(template)) {
            log.error("MessageConfigBiz.editMessageTemplate查询模板配置为空，templateCode={}",
                    editMessageTemplateBo.getTemplateCode());
            throw new BizException("MessageConfigBiz.editMessageTemplate查询模板配置为空");
        }
        template.setTemplateContent(editMessageTemplateBo.getTemplateContent());
        template.setUpdateTime(LocalDateTime.now());
        template.setUpdateBy(editMessageTemplateBo.getLoginUserId());
        templateService.updateById(template);
    }

    /**
     * 消息模板配置分页列表查询
     *
     * @param current
     * @param size
     * @param pageMessageTemplateBo
     * @return
     */
    public CommonPage<PageMessageTemplateBo> pageMessageTemplate(long current, long size,
            PageMessageTemplateBo pageMessageTemplateBo) {
        Page<PageMessageTemplateVo> pageInfo = templateService.pageMessageConfig(
                current,
                size,
                pageMessageTemplateBo.getSceneCode(),
                pageMessageTemplateBo.getChannelCode(),
                pageMessageTemplateBo.getGatewayCode());

        List<PageMessageTemplateVo> records = pageInfo.getRecords();
        List<PageMessageTemplateBo> result = this.convertPageMessageConfigVoToBo(records);

        CommonPage<PageMessageTemplateBo> commonPage = new CommonPage<>();
        commonPage.setCurrent(pageInfo.getCurrent());
        commonPage.setSize(pageInfo.getSize());
        commonPage.setTotal(pageInfo.getTotal());
        commonPage.setRecords(result);
        return commonPage;
    }

    /**
     * @param voList
     * @return
     */
    private List<PageMessageTemplateBo> convertPageMessageConfigVoToBo(List<PageMessageTemplateVo> voList) {
        if (CollectionUtil.isNotEmpty(voList)) {
            return voList.stream()
                    .filter(Objects::nonNull)
                    .map(o -> PageMessageTemplateBo.builder()
                            .sceneCode(o.getSceneCode())
                            .channelCode(o.getChannelCode())
                            .gatewayCode(o.getGatewayCode())
                            .gatewaySign(o.getGatewaySign())
                            .templateCode(o.getTemplateCode())
                            .templateContent(o.getTemplateContent())
                            .createBy(o.getCreateBy())
                            .createTime(o.getCreateTime())
                            .updateBy(o.getUpdateBy())
                            .updateTime(o.getUpdateTime())
                            .deleteStatus(o.getDeleteStatus())
                            .build()
                    ).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

}
