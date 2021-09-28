package heguang5460.github.io.message.web.controller;

import heguang5460.github.io.message.biz.bo.SendMessageBo;
import heguang5460.github.io.message.biz.service.MessageSendBiz;
import heguang5460.github.io.message.common.result.CommonResult;
import heguang5460.github.io.message.web.request.SendMessageRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author he guang
 * @date 2021/9/3 11:27
 */
@RestController
@RequestMapping("/message")
public class MessageSendController {

    @Autowired
    private MessageSendBiz messageSendBiz;

    @PostMapping("/send")
    public CommonResult sendMessage(@Valid @RequestBody SendMessageRequest sendMessageRequest){
        //转换参数
        SendMessageBo sendMessageBo = SendMessageBo.builder()
                .fromUser(sendMessageRequest.getFromUser())
                .toUser(sendMessageRequest.getToUser())
                .sceneCode(sendMessageRequest.getSceneCode())
                .paramMap(sendMessageRequest.getParamMap())
                .build();
        //调用业务
        messageSendBiz.sendMessage(sendMessageBo);
        //返回
        return CommonResult.success();
    }

}
