package com.qixinmini.microservice.bus.servuce.task;

import com.alibaba.fastjson.JSONObject;
import com.qixinmini.microservice.bus.api.model.MsgLog;
import com.qixinmini.microservice.bus.servuce.constant.MicroserviceRabbitConstant;
import com.qixinmini.microservice.bus.servuce.constant.MsgConstant;
import com.qixinmini.microservice.bus.servuce.service.MsgLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *  @Description 消息重新投递定时任务
 *  @author lijie
 *  @Date 2020/5/26 9:23
 */

@Slf4j
@RequiredArgsConstructor
public class MicroserviceMsgTask {


    private final MsgLogService msgLogService;

    private final RabbitTemplate rabbitTemplate;

    /**
     * @Description 每隔30秒消息重新投递
     * @author lijie
     * @createTime 2020-05-26 10:05:51
     * @param
     * @return void
     */

    public void reSentMsg(){
        log.info("消息重复投递开始----------------");
        // 查询所有的待投递消息
        List<MsgLog> msgLogs = msgLogService.selectDeliverMes();
        if(!CollectionUtils.isEmpty(msgLogs)){
            msgLogs.forEach(msgLog -> {
                String msgId = msgLog.getMsgId().toString();
                // 如果重试次数超过三次  则为失败
                if(msgLog.getTryCount()<= MsgConstant.MsgLogStatus.MAX_TRY_COUNT){
                    // 重试
                    CorrelationData correlationData = new CorrelationData(msgId);
                    rabbitTemplate.convertAndSend(MicroserviceRabbitConstant.RainbowExchange.EMAIL_EXCHANGE,
                            MicroserviceRabbitConstant.RainbowRoutingKey.EMAIL_ROUTING_KEY
                            ,JSONObject.toJSONString(msgLog),correlationData);
                    // 次数加一
                    msgLogService.updateTryCount(msgLog.getMsgId());
                }else{
                    // 状态改为失败
                    msgLogService.updateStatus(msgId, MsgConstant.MsgLogStatus.DELIVER_FAIL);
                    log.info("超过最大重试次数, 消息投递失败, msgId: {}", msgId);
                }
            });
        }
    }
}
