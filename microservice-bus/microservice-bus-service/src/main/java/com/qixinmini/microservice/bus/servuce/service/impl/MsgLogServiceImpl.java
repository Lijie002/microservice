package com.qixinmini.microservice.bus.servuce.service.impl;

import com.qixinmini.microservice.bus.api.model.MsgLog;
import com.qixinmini.microservice.bus.servuce.exception.BusException;
import com.qixinmini.microservice.bus.servuce.mapper.MsgLogMapper;
import com.qixinmini.microservice.bus.servuce.service.MsgLogService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  @Description 消息投递日志
 *  @author lijie
 *  @Date 2020/5/22 17:41
 */
@Service
@RequiredArgsConstructor
public class MsgLogServiceImpl implements MsgLogService {

    private final MsgLogMapper msgLogMapper;

    @Override
    public void updateStatus(String msgId, Integer delivering) {
        try {
            if(StringUtils.isNotBlank(msgId)){
                MsgLog msgLog = msgLogMapper.selectById(msgId);
                msgLog.setStatus(delivering);
                msgLogMapper.updateById(msgLog);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new BusException("更新消息状态失败");
        }
    }

    @Override
    public MsgLog selectById(String correlationId) {
        MsgLog msgLog =null;
        try {
             msgLog = msgLogMapper.selectById(correlationId);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusException("通过Id查询失败");
        }
        return msgLog;
    }

    @Override
    public List<MsgLog> selectDeliverMes() {
        List<MsgLog> msgLog =null;
        try {
            msgLog = msgLogMapper.selectDeliverMes();
        }catch (Exception e){
            e.printStackTrace();
            throw new BusException("查询待投递失败");
        }
        return msgLog;
    }

    @Override
    public void updateTryCount(Long msgId) {
        try {
            MsgLog msgLog = msgLogMapper.selectById(msgId);
            msgLog.setTryCount(msgLog.getTryCount()+1);
            msgLogMapper.updateById(msgLog);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusException("更新最大投递次数失败");
        }
    }
}
