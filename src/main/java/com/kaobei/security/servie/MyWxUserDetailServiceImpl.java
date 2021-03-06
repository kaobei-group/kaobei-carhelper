package com.kaobei.security.servie;

import com.kaobei.entity.UserEntity;
import com.kaobei.entity.UserRoleEntity;
import com.kaobei.security.entity.MyUserDetails;
import com.kaobei.service.UserService;
import com.kaobei.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyWxUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private WxUtils wxUtils;

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String openId) throws UsernameNotFoundException {
        UserEntity user = userService.findUserByOpenId(openId);


        if (user==null){
            userService.insertUser(new UserEntity(openId,null,0.00));
            userService.setInitRole(new UserRoleEntity(openId,"user"));
        }

        List<UserRoleEntity> userRoleEntities = userService.findUserRolesByOpenId(openId);

        List<GrantedAuthority> authoritys = new ArrayList<>();

        for (UserRoleEntity userRoleEntity:userRoleEntities){
            authoritys.add(new SimpleGrantedAuthority(userRoleEntity.getRole()));
        }

        return new MyUserDetails(openId,"vxLogin",authoritys);

    }
}
