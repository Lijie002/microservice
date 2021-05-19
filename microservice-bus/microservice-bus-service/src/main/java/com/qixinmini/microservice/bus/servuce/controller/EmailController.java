package com.qixinmini.microservice.bus.servuce.controller;

import com.qixinmini.microservice.bus.api.model.MicroserviceMail;
import com.qixinmini.microservice.bus.servuce.product.EmailProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @Description 发送邮件
 *  @author lijie
 *  @Date 2020/5/25 16:04
 */
@Api(tags = "邮件服务")
@RestController
@RequestMapping("email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailProducer producer;

    /**
     * @Description 发送邮件
     * @author lijie
     * @createTime 2020-05-25 15:50:39
     * @param rainbowMail
     * @return org.springframework.http.ResponseEntity
     */
    @ApiOperation("发送邮件")
    @PostMapping
    public ResponseEntity send(@RequestBody MicroserviceMail rainbowMail){
        producer.send(rainbowMail);
        return ResponseEntity.ok().build();
    }
}
