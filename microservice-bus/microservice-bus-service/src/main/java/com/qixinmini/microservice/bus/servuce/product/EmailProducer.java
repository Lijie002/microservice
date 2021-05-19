package com.qixinmini.microservice.bus.servuce.product;

import com.qixinmini.common.core.utils.JsonUtil;
import com.qixinmini.microservice.bus.api.model.MicroserviceMail;
import com.qixinmini.microservice.bus.api.model.MsgLog;
import com.qixinmini.microservice.bus.servuce.constant.MicroserviceRabbitConstant;
import com.qixinmini.microservice.bus.servuce.constant.MsgConstant;
import com.qixinmini.microservice.bus.servuce.exception.BusException;
import com.qixinmini.microservice.bus.servuce.mapper.MsgLogMapper;
import com.qixinmini.microservice.bus.servuce.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *  @Description
 *  @author liuhu
 *  @Date 2020/5/23 15:29
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EmailProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private MsgLogMapper msgLogMapper;

    /**
     * @Description 发送邮件业务
     * @author liuhu
     * @createTime 2020-05-25 16:03:38
     * @param microserviceMail
     * @return void
     */
    public void send(MicroserviceMail microserviceMail) {
       try {
           MsgLog msgLog = MsgLog.builder().msg(JsonUtil.objToStr(microserviceMail)).status(MsgConstant.MsgLogStatus.DELIVERING).
                   exchange(MicroserviceRabbitConstant.RainbowExchange.EMAIL_EXCHANGE)
                   .routingKey(MicroserviceRabbitConstant.RainbowRoutingKey.EMAIL_ROUTING_KEY).createTime(new Date()).build();
           msgLogMapper.insert(msgLog);
           // 消息ID
           CorrelationData correlationData = new CorrelationData(msgLog.getMsgId().toString());
           rabbitTemplate.convertAndSend(MicroserviceRabbitConstant.RainbowExchange.EMAIL_EXCHANGE,
                   MicroserviceRabbitConstant.RainbowRoutingKey.EMAIL_ROUTING_KEY, MessageHelper.objToMsg(microserviceMail),correlationData);
       }catch (Exception e){
           e.printStackTrace();
           throw new BusException("发送邮件失败");
       }
    }
}
