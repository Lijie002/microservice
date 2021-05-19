package com.qixinmini.microservice.bus.servuce.listener;

import com.qixinmini.microservice.bus.servuce.constant.MicroserviceRabbitConstant;
import com.qixinmini.microservice.bus.servuce.consumer.EmailConsumer;
import com.qixinmini.microservice.bus.servuce.proxy.BaseConsumer;
import com.qixinmini.microservice.bus.servuce.proxy.BaseConsumerProxy;
import com.qixinmini.microservice.bus.servuce.service.MsgLogService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *  @Description 邮件消息监听器
 *  @author lijie
 *  @Date 2020/5/23 15:21
 */
@Component
@RequiredArgsConstructor
public class EmailListener {

    private final EmailConsumer emailConsumer;

    private final MsgLogService msgLogService;

    /**
     * @Description 执行逻辑
     * @author lijie
     * @createTime 2020-05-25 15:33:01
     * @param message
     * @param channel
     * @return void
     */
    @RabbitListener(queues = MicroserviceRabbitConstant.RainbowQueue.EMAIL_QUEUE)
    public void handler(Message message, Channel channel) throws IOException {
        // 代理
        BaseConsumerProxy proxy = new BaseConsumerProxy(emailConsumer,msgLogService);
        BaseConsumer baseConsumer = (BaseConsumer)proxy.proxy();
        if(null != baseConsumer){
            baseConsumer.consume(message,channel);
        }
    }
}
