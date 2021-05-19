package com.qixinmini.microservice.bus.servuce.service;

import com.qixinmini.microservice.bus.api.model.MsgLog;

import java.util.List;

/**
 *  @Description 消息投递日志
 *  @author lijie
 *  @Date 2020/5/22 17:40
 */

public interface MsgLogService{
   /**
    * @Description 修改消息状态
    * @author lijie
    * @createTime 2020-05-22 18:10:07
    * @param msgId
    * @param delivering
    * @return void
    */
    void updateStatus(String msgId, Integer delivering);
    /**
     * @Description 根据ID查询
     * @author lijie
     * @createTime 2020-05-23 14:41:05
     * @param correlationId
     * @return com.rainbow.bus.api.entity.MsgLog
     */
    MsgLog selectById(String correlationId);

    /**
     * @Description 查询待投递消息
     * @author lijie
     * @createTime 2020-05-26 09:31:28
     * @param
     * @return com.rainbow.bus.api.entity.MsgLog
     */
    List<MsgLog> selectDeliverMes();

    /**
     * @Description 更新尝试
     * @author lijie
     * @createTime 2020-05-26 09:48:05
     * @param msgId
     * @return void
     */
    void updateTryCount(Long msgId);
}
