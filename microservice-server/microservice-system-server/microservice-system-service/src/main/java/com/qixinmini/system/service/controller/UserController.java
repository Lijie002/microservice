package com.qixinmini.system.service.controller;

import com.qixinmini.common.core.model.QueryRequest;
import com.qixinmini.common.core.model.system.SystemUser;
import com.qixinmini.common.core.utils.MicroserviceUtil;
import com.qixinmini.system.service.annotation.RainbowLog;
import com.qixinmini.system.service.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *  @Description 用户接口
 *  @author liuhu
 *  @Date 2020/5/26 14:53
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Api(tags = "系统用户接口")
public class UserController {

    private final UserService userService;
    /**
     * @Description 登录成功回调
     * @author liuhu
     * @createTime 2020-05-26 14:55:33
     * @param
     * @return void
     */
    @GetMapping("success")
    @RainbowLog(description = "登录")
    @ApiOperation("登录成功回调")
    public ResponseEntity success(){
        userService.success();
     // todo 记录登录日志  发送邮件
      return ResponseEntity.ok().build();
    }

    /**
     * @Description 用户分页列表
     * @author liuhu
     * @createTime 2020-05-26 15:57:02
     * @param queryRequest
     * @param user
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("page")
    @PreAuthorize("hasAuthority('user:view')")
    @ApiOperation("查询用户列表分页")
    public ResponseEntity userPage(QueryRequest queryRequest, SystemUser user){
            return ResponseEntity.ok(MicroserviceUtil.buildTableData(userService.userPage(queryRequest,user)));
    }

    /**
     * @Description 新增用户
     * @author liuhu
     * @createTime 2020-05-26 17:31:04
     * @param user
     * @return org.springframework.http.ResponseEntity
     */
    @PostMapping
    @PreAuthorize("hasAuthority('user:add')")
    @ApiOperation("新增用户")
    public ResponseEntity addUser(@RequestBody SystemUser user){
        return ResponseEntity.ok(userService.addUser(user));
    }

    /**
     * @Description 删除用户
     * @author liuhu
     * @createTime 2020-05-26 17:31:04
     * @param ids
     * @return org.springframework.http.ResponseEntity
     */
    @DeleteMapping("{ids}")
    @PreAuthorize("hasAuthority('user:delete')")
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "ids",value = "用户ID集合")
    public ResponseEntity deleteUser(@PathVariable("ids")String ids){
        userService.deleteUser(ids);
        return ResponseEntity.ok().build();
    }

    /**
     * @Description 重置密码
     * @author liuhu
     * @createTime 2020-05-26 17:31:04
     * @param userId
     * @return org.springframework.http.ResponseEntity
     */
    @PutMapping("password")
    @PreAuthorize("hasAuthority('user:reset')")
    @ApiOperation("重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID"),
            @ApiImplicitParam(name = "newPassword",value = "新密码"),
            @ApiImplicitParam(name = "oldPassword",value = "旧密码")
    })
    public ResponseEntity resetPassword(@RequestParam("newPassword")String newPassword,
                                        @RequestParam("oldPassword")String oldPassword,
                                        @RequestParam("id")Long userId){
        userService.resetPassword(userId,newPassword,oldPassword);
        return ResponseEntity.ok().build();
    }

    /**
     * @Description 根据用户名得到id
     * @author liuhu
     * @createTime 2020-05-26 17:31:04
     * @param userId
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("{userId}")
    @PreAuthorize("hasAuthority('user:view')")
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "userId",value = "用户ID")
    public ResponseEntity getUser(@PathVariable("userId")long userId){
        userService.getUser(userId);
        return ResponseEntity.ok().build();
    }
}
