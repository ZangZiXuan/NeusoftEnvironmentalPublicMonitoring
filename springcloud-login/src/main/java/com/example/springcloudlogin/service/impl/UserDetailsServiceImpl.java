//package com.example.springcloudlogin.service.impl;
//
///**
// * @Author Zang Xinrui
// * @Description TODO
// * @Date 2024/6/15 20:27
// * @Version 1.0
// */
//import com.example.springcloudapi.dao.entity.Griddler;
//import com.example.springcloudapi.dao.entity.Manager;
//import com.example.springcloudapi.dao.entity.PolicyMaker;
//import com.example.springcloudapi.dao.entity.Public;
//import com.example.springcloudlogin.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserService.AuthResult authResult = (UserService.AuthResult) userService.authenticateUser(username, null); // 查找用户时不需要密码
//
//        if (authResult == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        String password = getPasswordFromUser(authResult.getUser());
//        String role = "ROLE_USER"; // 根据业务需求设置角色
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(username)
//                .password(password)
//                .roles(role)
//                .build();
//    }
//
//    private String getPasswordFromUser(Object user) {
//        if (user instanceof Griddler) {
//            return ((Griddler) user).getPassword();
//        } else if (user instanceof Manager) {
//            return ((Manager) user).getPassword();
//        } else if (user instanceof PolicyMaker) {
//            return ((PolicyMaker) user).getPassword();
//        } else if (user instanceof Public) {
//            return ((Public) user).getPassword();
//        }
//        return null;
//    }
//}
//
