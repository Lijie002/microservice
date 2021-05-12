package com.qixinmini.system.api.feign;

import com.qixinmini.system.api.dto.MicroserviceMail;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *  @Description 邮件服务feign
 *  @author lijie
 *  @Date 2021/5/11 20:39
 */
@FeignClient("rainbow-bus")
public interface RainbowMailFeign {

    /**
     * @Description 发送邮件
     * @author lijie
     * @createTime 2021-05-11 20:50:39
     * @param rainbowMail
     * @return org.springframework.http.ResponseEntity
     */
    @ApiOperation("发送邮件")
    @PostMapping("email")
    ResponseEntity send(@RequestBody MicroserviceMail rainbowMail);

}
