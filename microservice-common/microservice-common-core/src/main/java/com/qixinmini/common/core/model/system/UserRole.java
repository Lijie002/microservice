package com.qixinmini.common.core.model.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *  @Description 用户角色中间表
 *  @author lijie
 *  @Date 2020/11/26 16:45
 */
@Data
@TableName("t_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -3166012934498268403L;

    @TableId(value = "USER_ID")
    private Long userId;

    @TableId(value = "ROLE_ID")
    private Long roleId;

}