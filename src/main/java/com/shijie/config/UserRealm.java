package com.shijie.config;

import com.shijie.pojo.User;
import com.shijie.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserServiceImpl userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //授予权限
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        //拿到User对象
        Subject subject=SecurityUtils.getSubject();
        User currentUser=(User) subject.getPrincipal();

        info.addStringPermission(Integer.toString(currentUser.getUser_grade()));

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //用户名/密码
        System.out.println("doGetAuthenticationInfo");
        UsernamePasswordToken userToken =(UsernamePasswordToken) authenticationToken;
        System.out.println("======="+userToken.getUsername());
        User user=userService.queryUserByName(userToken.getUsername());
        if (user == null){
            return null;
        }
        SecurityUtils.getSubject().getSession().setAttribute("loginUser",user);
        return new SimpleAuthenticationInfo(user,user.getUser_password(),"");
    }
}
