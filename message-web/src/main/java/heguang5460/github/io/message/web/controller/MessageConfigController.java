package heguang5460.github.io.message.web.controller;

import cn.hutool.core.bean.BeanUtil;
import heguang5460.github.io.message.biz.bo.EditMessageGatewayBo;
import heguang5460.github.io.message.biz.bo.EditMessageTemplateBo;
import heguang5460.github.io.message.biz.bo.ListMessageChannelBo;
import heguang5460.github.io.message.biz.bo.ListMessageGatewayBo;
import heguang5460.github.io.message.biz.bo.PageMessageTemplateBo;
import heguang5460.github.io.message.biz.bo.SaveMessageChannelBo;
import heguang5460.github.io.message.biz.bo.SaveMessageGatewayBo;
import heguang5460.github.io.message.biz.bo.SaveMessageTemplateBo;
import heguang5460.github.io.message.biz.service.MessageConfigBiz;
import heguang5460.github.io.message.common.result.CommonPage;
import heguang5460.github.io.message.common.result.CommonResult;
import heguang5460.github.io.message.dao.enums.ChannelCodeEnum;
import heguang5460.github.io.message.dao.enums.GatewayCodeEnum;
import heguang5460.github.io.message.web.request.EditMessageGatewayRequest;
import heguang5460.github.io.message.web.request.EditMessageTemplateRequest;
import heguang5460.github.io.message.web.request.PageMessageTemplateRequest;
import heguang5460.github.io.message.web.request.SaveMessageChannelRequest;
import heguang5460.github.io.message.web.request.SaveMessageGatewayRequest;
import heguang5460.github.io.message.web.request.SaveMessageTemplateRequest;
import heguang5460.github.io.message.web.response.ListMessageChannelResponse;
import heguang5460.github.io.message.web.response.ListMessageGatewayResponse;
import heguang5460.github.io.message.web.response.PageMessageTemplateResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息配置接口
 *
 * @author he guang
 */
@RestController
@RequestMapping("/message")
public class MessageConfigController {

    @Autowired
    private MessageConfigBiz messageConfigBiz;

    @PostMapping("/channel/save")
    public CommonResult saveMessageChannel(@Valid @RequestBody SaveMessageChannelRequest saveMessageChannelRequest) {
        // 参数转换
        SaveMessageChannelBo saveMessageChannelBo = SaveMessageChannelBo.builder()
                .channelCode(ChannelCodeEnum.find(saveMessageChannelRequest.getChannelCode()))
                .loginUserId(saveMessageChannelRequest.getLoginUserId())
                .build();
        // 配置消息渠道
        messageConfigBiz.saveMessageChannel(saveMessageChannelBo);
        return CommonResult.success();
    }

    @GetMapping("/channel/list")
    public CommonResult<List<ListMessageChannelResponse>> listMessageChannel() {
        // 列表消息渠道
        List<ListMessageChannelBo> boList = messageConfigBiz.listMessageChannel();
        // 返回转换
        List<ListMessageChannelResponse> data = boList.stream()
                .filter(Objects::nonNull)
                .map(o -> {
                    ListMessageChannelResponse resp = new ListMessageChannelResponse();
                    BeanUtil.copyProperties(o, resp);
                    return resp;
                }).collect(Collectors.toList());
        return CommonResult.success(data);
    }

    @PostMapping("/gateway/save")
    public CommonResult saveMessageGateway(@Valid @RequestBody SaveMessageGatewayRequest saveMessageGatewayRequest) {
        // 参数转换
        SaveMessageGatewayBo saveMessageGatewayBo = SaveMessageGatewayBo.builder()
                .channelCode(ChannelCodeEnum.find(saveMessageGatewayRequest.getChannelCode()))
                .gatewayCode(GatewayCodeEnum.find(saveMessageGatewayRequest.getGatewayCode()))
                .gatewayAccount(saveMessageGatewayRequest.getGatewayAccount())
                .gatewayPassword(saveMessageGatewayRequest.getGatewayPassword())
                .gatewaySign(saveMessageGatewayRequest.getGatewaySign())
                .loginUserId(saveMessageGatewayRequest.getLoginUserId())
                .build();
        // 配置消息网关
        messageConfigBiz.saveMessageGateway(saveMessageGatewayBo);
        return CommonResult.success();
    }

    @PostMapping("/gateway/edit")
    public CommonResult editMessageGateway(@Valid @RequestBody EditMessageGatewayRequest editMessageGatewayRequest) {
        // 参数转换
        EditMessageGatewayBo editMessageGatewayBo = EditMessageGatewayBo.builder()
                .gatewayCode(GatewayCodeEnum.find(editMessageGatewayRequest.getGatewayCode()))
                .gatewayAccount(editMessageGatewayRequest.getGatewayAccount())
                .gatewayPassword(editMessageGatewayRequest.getGatewayPassword())
                .gatewaySign(editMessageGatewayRequest.getGatewaySign())
                .loginUserId(editMessageGatewayRequest.getLoginUserId())
                .build();
        // 编辑修改消息网关
        messageConfigBiz.editMessageGateway(editMessageGatewayBo);
        return CommonResult.success();
    }

    @GetMapping("/gateway/list")
    public CommonResult<List<ListMessageGatewayResponse>> listMessageGateway() {
        // 列表消息渠道
        List<ListMessageGatewayBo> boList = messageConfigBiz.listMessageGateway();
        // 返回转换
        List<ListMessageGatewayResponse> data = boList.stream()
                .filter(Objects::nonNull)
                .map(o -> {
                    ListMessageGatewayResponse resp = new ListMessageGatewayResponse();
                    BeanUtil.copyProperties(o, resp);
                    return resp;
                }).collect(Collectors.toList());
        return CommonResult.success(data);
    }

    @PostMapping("/template/save")
    public CommonResult saveMessageTemplate(@Valid @RequestBody SaveMessageTemplateRequest saveMessageTemplateRequest) {
        // 参数转换
        SaveMessageTemplateBo saveMessageTemplateBo = SaveMessageTemplateBo.builder()
                .sceneCode(saveMessageTemplateRequest.getSceneCode().toUpperCase())
                .channelCode(ChannelCodeEnum.find(saveMessageTemplateRequest.getChannelCode()))
                .gatewayCode(GatewayCodeEnum.find(saveMessageTemplateRequest.getGatewayCode()))
                .templateContent(saveMessageTemplateRequest.getTemplateContent())
                .loginUserId(saveMessageTemplateRequest.getLoginUserId())
                .build();
        // 配置消息
        messageConfigBiz.saveMessageTemplate(saveMessageTemplateBo);
        return CommonResult.success();
    }

    @PostMapping("/template/edit")
    public CommonResult editMessageTemplate(@Valid @RequestBody EditMessageTemplateRequest editMessageTemplateRequest) {
        // 参数转换
        EditMessageTemplateBo editMessageTemplateBo = EditMessageTemplateBo.builder()
                .templateCode(editMessageTemplateRequest.getTemplateCode())
                .templateContent(editMessageTemplateRequest.getTemplateContent())
                .loginUserId(editMessageTemplateRequest.getLoginUserId())
                .build();
        // 配置消息
        messageConfigBiz.editMessageTemplate(editMessageTemplateBo);
        return CommonResult.success();
    }

    @GetMapping("/template/page")
    public CommonResult<CommonPage<PageMessageTemplateResponse>> pageMessageTemplate(
            PageMessageTemplateRequest pageMessageTemplateRequest) {
        // 参数转换
        PageMessageTemplateBo pageMessageTemplateBo = PageMessageTemplateBo.builder()
                .sceneCode(pageMessageTemplateRequest.getSceneCode())
                .channelCode(pageMessageTemplateRequest.getChannelCode())
                .gatewayCode(pageMessageTemplateRequest.getGatewayCode())
                .build();
        // 配置分页列表
        CommonPage<PageMessageTemplateBo> commonPageBo = messageConfigBiz.pageMessageTemplate(
                pageMessageTemplateRequest.getCurrent(), pageMessageTemplateRequest.getSize(), pageMessageTemplateBo);
        // 返回转换
        CommonPage<PageMessageTemplateResponse> commonPage = new CommonPage<>();
        commonPage.setTotal(commonPageBo.getTotal());
        commonPage.setSize(commonPageBo.getSize());
        commonPage.setCurrent(commonPageBo.getCurrent());
        commonPage.setRecords(commonPageBo.getRecords().stream()
                .filter(Objects::nonNull)
                .map(o -> {
                    PageMessageTemplateResponse resp = new PageMessageTemplateResponse();
                    BeanUtil.copyProperties(o, resp);
                    return resp;
                }).collect(Collectors.toList()));
        return CommonResult.success(commonPage);
    }
}
