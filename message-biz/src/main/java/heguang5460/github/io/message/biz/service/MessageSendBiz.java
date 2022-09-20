package heguang5460.github.io.message.biz.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import heguang5460.github.io.message.biz.bo.SendMessageBo;
import heguang5460.github.io.message.biz.ex.BizException;
import heguang5460.github.io.message.common.enums.MQExchangeEnum;
import heguang5460.github.io.message.core.model.SceneChannelMQModel;
import heguang5460.github.io.message.core.mq.producer.MQProducer;
import heguang5460.github.io.message.dao.domain.db.Task;
import heguang5460.github.io.message.dao.domain.vo.SceneChannelVo;
import heguang5460.github.io.message.dao.service.SceneService;
import heguang5460.github.io.message.dao.service.TaskService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author he guang
 * @date 2021/9/3 12:52
 */
@Slf4j
@Service
public class MessageSendBiz {

    @Autowired
    private MQProducer mqProducer;
    @Autowired
    private TaskService taskService;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private ExecutorService executorService;

    /**
     * @param sendMessageBo
     */
    public void sendMessage(SendMessageBo sendMessageBo) {
        // 查询场景关联的所有渠道
        List<SceneChannelVo> sceneChannelVos = sceneService.queryChannelsByRelation(sendMessageBo.getSceneCode());
        if (CollectionUtil.isEmpty(sceneChannelVos)) {
            log.error("MessageSendBiz.sendMessage 查询场景和渠道信息为空，参数sceneCode={}", sendMessageBo.getSceneCode());
            throw new BizException("MessageSendBiz.sendMessage 查询场景和渠道信息为空");
        }
        // 根据渠道分发 各渠道异步处理
        for (SceneChannelVo sceneChannelVo : sceneChannelVos) {
            this.sendMessagePerChannel(sendMessageBo, sceneChannelVo);
        }
    }

    /**
     * 异步将各个消息推到对应渠道的MQ Exchange
     *
     * @param sendMessageBo
     * @param sceneChannelVo
     */
    private void sendMessagePerChannel(SendMessageBo sendMessageBo, SceneChannelVo sceneChannelVo) {
        log.info("场景码sceneCode={},渠道码channelCode={}投递MQ消息到对应渠道交换机",
                sendMessageBo.getSceneCode(), sceneChannelVo.getChannelCode());
        executorService.submit(() -> {
            // 生成任务
            Task task = taskService.buildTaskEntity(
                    sceneChannelVo.getSceneId(),
                    sceneChannelVo.getChannelId(),
                    JSONUtil.toJsonStr(sendMessageBo));
            // 推mq
            SceneChannelMQModel sceneChannelMQModel = new SceneChannelMQModel();
            sceneChannelMQModel.setTaskId(task.getId());
            sceneChannelMQModel.setSceneId(sceneChannelVo.getSceneId());
            sceneChannelMQModel.setSceneCode(sendMessageBo.getSceneCode());
            sceneChannelMQModel.setChannelId(sceneChannelVo.getChannelId());
            sceneChannelMQModel.setChannelCode(sceneChannelVo.getChannelCode());
            sceneChannelMQModel.setFromUser(sendMessageBo.getFromUser());
            sceneChannelMQModel.setToUser(sendMessageBo.getToUser());
            sceneChannelMQModel.setParamMap(sendMessageBo.getParamMap());
            mqProducer.produce(
                    MQExchangeEnum.EXCHANGE_TASK.name(),
                    MQExchangeEnum.EXCHANGE_TASK.name(),
                    sceneChannelMQModel,
                    task.getId().toString()
            );
        });
    }
}
