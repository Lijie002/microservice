package com.qixinmini.microservice.bus.servuce.proxy;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 *  @Description 消费者都需要继承此接口 动态代理增强
 *  @author lijie
 *  @Date 2020/5/23 14:09
 */
public interface BaseConsumer {
    /**
     * @Description 消费消息
     * @author lijie
     * @createTime 2020-05-23 14:10:20
     * @param message
     * @param channel 信道
     * @return void
     */
    void consume(Message message, Channel channel) throws IOException;
}
