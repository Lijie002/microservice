package com.qixinmini.microservice.bus.api.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/**
 *  @Description 消息投递日志
 *  @author lijie
 *  @Date 2021/11/22 17:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgLog implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 消息唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Long msgId;
    /**
     * 消息体, json格式化
     */
    private String msg;
    /**
     * 交换机
     */
    private String exchange;
    /**
     * 路由键
     */
    private String routingKey;
    /**
     * 状态: 0投递中 1投递成功 2投递失败 3已消费
     */
    private Integer status;
    /**
     * 重试次数
     */
    private Integer tryCount;
    /**
     * 下一次重试时间
     */
    private Date nextTryTime;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
