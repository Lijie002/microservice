package com.qixinmini.microservice.bus.servuce.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qixinmini.microservice.bus.api.model.MsgLog;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MsgLogMapper extends BaseMapper<MsgLog> {
    /**
     * @Description 查询待投递消息失败
     * @author lijie
     * @createTime 2020-05-26 09:33:43
     * @param
     * @return java.util.List<com.rainbow.bus.api.entity.MsgLog>
     */
    List<MsgLog> selectDeliverMes();
}
