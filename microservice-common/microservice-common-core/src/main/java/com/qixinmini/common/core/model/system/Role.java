package com.qixinmini.common.core.model.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  @Description 角色实体
 *  @author lijie
 *  @Date 2020/11/27 13:28
 */
@Data
@TableName("t_role")
@ApiModel("角色实体")
public class Role implements Serializable {

    private static final long serialVersionUID = -1714476694755654924L;

    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;

    @TableField(value = "ROLE_NAME")
    private String roleName;

    @TableField(value = "REMARK")
    private String remark;

    @TableField(value = "CREATE_TIME")
    private Date createTime;

    @TableField(value = "MODIFY_TIME")
    private Date modifyTime;

    @TableField(exist = false)
    @ApiModelProperty(name = "menuIds",value = "菜单id集合")
    private  String menuIds;

}