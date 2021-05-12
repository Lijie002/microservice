package com.qixinmini.system.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qixinmini.common.core.model.system.Log;
import org.springframework.stereotype.Repository;

/**
 *  @Description 用户操作日志表
 *  @author lijie
 *  @Date 2020-07-04 11:10:55
 */
@Repository
public interface LogMapper extends BaseMapper<Log> {

}
