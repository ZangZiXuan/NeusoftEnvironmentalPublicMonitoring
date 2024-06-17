package com.example.springcloudapi.controller;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/15 20:31
 * @Version 1.0
 */
import com.example.springcloudapi.service.UserService;
import com.example.springcloudapi.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            // 调用 UserService 中的 authenticateUser 方法进行认证
            Object authResult = userService.authenticateUser(username, MD5Util.encode(password));

            if (authResult != null) {
                // 这里可以生成 JWT token 或者其他处理
                return ResponseEntity.ok(authResult);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}
