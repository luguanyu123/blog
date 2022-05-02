package com.lu.config;

import com.lu.po.User;
import com.lu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {//真正进行用户认证和授权
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入认证方法");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        User user = userService.login2(token.getUsername());
        //将用户放入session
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("user", user);

        if (user == null){
            //用户名不存在，return null会抛出UnknownAccountException异常
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
