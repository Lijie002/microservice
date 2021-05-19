package com.qixinmini.microservice.bus.servuce.consumer;


import com.qixinmini.microservice.bus.api.model.MicroserviceMail;
import com.qixinmini.microservice.bus.servuce.exception.BusException;
import com.qixinmini.microservice.bus.servuce.proxy.BaseConsumer;
import com.qixinmini.microservice.bus.servuce.utils.MessageHelper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *  @Description email消费者
 *  @author lijie
 *  @Date 2020/5/23 14:59
 */
@Component
@RequiredArgsConstructor
public class EmailConsumer implements BaseConsumer {

    private final JavaMailSender javaMailSender;

    @Override
    public void consume(Message message, Channel channel) throws IOException {
        try {
            // 解析邮件对象  发送邮件
            MicroserviceMail microserviceMail = MessageHelper.msgToObj(message, MicroserviceMail.class);
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setFrom(microserviceMail.getFromMailAddress());
            simpleMailMessage.setTo(microserviceMail.getToMailAddress());
            simpleMailMessage.setSubject(microserviceMail.getSubject());
            simpleMailMessage.setText(microserviceMail.getText());
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusException("发送邮件失败");
        }

    }
}
