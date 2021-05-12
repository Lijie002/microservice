package com.qixinmini.oauth.service.impl;

import com.qixinmini.common.core.model.MicroserviceAuthUser;
import com.qixinmini.common.core.model.system.SystemUser;
import com.qixinmini.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  @Description security 用户信息校验类
 *  @author lijie
 *  @Date 2021/5/10 18:32
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MicroserviceUserDetailService implements UserDetailsService {

    private final UserService userService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = userService.selectUserByUsername(username);
        if(null != user){
            String permissions = userService.findUserPermissions(username);
            // 是否锁定
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, user.getStatus())) {
                notLocked = true;
            }
            // 权限集合
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.NO_AUTHORITIES;
            if (StringUtils.isNotBlank(permissions)) {
                grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(permissions);
            }
            // 构建返回用户信息
            MicroserviceAuthUser authUser = new MicroserviceAuthUser(user.getUsername(), user.getPassword(), true, true, true, notLocked,
                    grantedAuthorities);
            // 属性拷贝
            BeanUtils.copyProperties(user, authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}
