package com.qixinmini.microservice.bus.servuce.constant;

public interface MicroserviceRabbitConstant {
    interface RainbowQueue{
        String EMAIL_QUEUE= "email.queue";
    }
    interface RainbowExchange{
        String EMAIL_EXCHANGE= "email.exchange";
    }
    interface RainbowRoutingKey{
        String EMAIL_ROUTING_KEY= "mail.routing.key";
    }
}
