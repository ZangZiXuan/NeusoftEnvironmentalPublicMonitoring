//package com.example.springcloudlogin.service.impl;
//
//import com.example.springcloudapi.dao.dto.Dept;
//import com.example.springcloudapi.dao.dto.LoginDTO;
//import com.example.springcloudlogin.feign.GriddlerFeignService;
//import com.example.springcloudlogin.feign.ManagerFeignService;
//import com.example.springcloudlogin.feign.PolicyMakerFeignService;
//import com.example.springcloudlogin.feign.PublicFeignService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Map;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    GriddlerFeignService griddlerFeignService;
//
//    @Autowired
//    ManagerFeignService managerFeignService;
//
//    @Autowired
//    PublicFeignService publicFeignService;
//
//    @Autowired
//    PolicyMakerFeignService policyMakerFeignService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // You need to define how to get the password for the user
//        String password = "$2a$10$7wDq/J8D6XwFgHDBfnYVEOka2frjZ0obXOzwiH7hJXt.lULGohdAe"; // Mock password encoded with BCrypt
//
//        LoginDTO loginDTO = new LoginDTO();
//        loginDTO.setUsername(username);
//
//        Dept dept = getUserDepartment(loginDTO);
//
//        if (dept.getDname() == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return User
//                .withUsername(username)
//                .password(password)
//                .roles(dept.getDname().toUpperCase()) // Set the role based on the department name
//                .build();
//    }
//
//    private Dept getUserDepartment(LoginDTO loginDTO) {
//        Dept dept = new Dept();
//
//        Map<String, Object> griddler = griddlerFeignService.findGriddler(loginDTO);
//        if (griddler.get("data") != null) {
//            dept.setDname("griddler");
//            dept.setUser(griddler.get("data"));
//            return dept;
//        }
//
//        Map<String, Object> manager = managerFeignService.findManager(loginDTO);
//        if (manager.get("data") != null) {
//            dept.setDname("manager");
//            dept.setUser(manager.get("data"));
//            return dept;
//        }
//
//        Map<String, Object> policyMaker = policyMakerFeignService.findPolicyMaker(loginDTO);
//        if (policyMaker.get("data") != null) {
//            dept.setDname("policyMaker");
//            dept.setUser(policyMaker.get("data"));
//            return dept;
//        }
//
//        Map<String, Object> aPublic = publicFeignService.findPublic(loginDTO);
//        if (aPublic.get("data") != null) {
//            dept.setDname("public");
//            dept.setUser(aPublic.get("data"));
//            return dept;
//        }
//
//        return dept; // If no user is found, the dept.dname will be null
//    }
//}
