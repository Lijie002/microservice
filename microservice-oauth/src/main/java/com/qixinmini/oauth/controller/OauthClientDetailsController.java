package com.qixinmini.oauth.controller;

import com.qixinmini.common.core.model.QueryRequest;
import com.qixinmini.common.core.model.system.OauthClientDetails;
import com.qixinmini.common.core.utils.MicroserviceUtil;
import com.qixinmini.oauth.service.OauthClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 *  @Description 客户端信息
 *  @author lijie
 *  @Date 2020/5/28 10:12
 */
@Api(tags = "客户端信息接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("client")
public class OauthClientDetailsController {

    private final OauthClientDetailsService oauthClientDetailsService;

    /**
     * @Description 根据id查询
     * @author lijie
     * @createTime 2021-05-12 10:26:26
     * @param clientId
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("{clientId}")
    @PreAuthorize("hasAuthority('client:view')")
    @ApiOperation("分页查询")
    public ResponseEntity findById(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(this.oauthClientDetailsService.findById(clientId));
    }

    /**
     * @Description 分页
     * @author lijie
     * @createTime 2021-05-12 10:26:26
     * @param request
     * @param oAuthClientDetails
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("page")
    @PreAuthorize("hasAuthority('client:view')")
    @ApiOperation("分页查询")
    public ResponseEntity oauthClientDetailsList(QueryRequest request, OauthClientDetails oAuthClientDetails) {
        return ResponseEntity.ok(MicroserviceUtil.buildTableData(this.oauthClientDetailsService.findOauthClientDetails(request, oAuthClientDetails)));
    }

     /**
      * @Description 新增客户端信息
      * @author lijie
      * @createTime 2021-05-12 09:19:32
      * @param oAuthClientDetails
      * @return void
      */
    @PostMapping
    @PreAuthorize("hasAuthority('client:add')")
    @ApiOperation("新增客户端")
    public ResponseEntity addOauthClientDetails(@Valid @RequestBody OauthClientDetails oAuthClientDetails)  {
     return    ResponseEntity.ok(oauthClientDetailsService.addOauthClientDetails(oAuthClientDetails));
    }

    /**
     * @Description 删除客户端
     * @author lijie
     * @createTime 2021-05-12 10:27:13
     * @param clientIds
     * @return void
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('client:delete')")
    @ApiOperation("删除客户端信息")
    public ResponseEntity deleteOauthClientDetails(@NotBlank(message = "{required}") @RequestParam("clientIds") String clientIds) {
              oauthClientDetailsService.deleteOauthClientDetails(clientIds);
              return ResponseEntity.ok().build();
    }

    /**
     * @Description 修改客户端信息
     * @author lijie
     * @createTime 2021-05-12 10:28:32
     * @param oAuthClientDetails
     * @return void
     */
    @PutMapping
    @PreAuthorize("hasAuthority('client:update')")
    @ApiOperation("修改客户端信息")
    public void updateOauthClientDetails(@Valid OauthClientDetails oAuthClientDetails)  {

    }
}
