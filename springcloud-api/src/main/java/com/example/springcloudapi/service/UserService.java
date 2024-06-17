package com.example.springcloudapi.service;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/15 20:23
 * @Version 1.0
 */
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springcloudapi.dao.entity.Griddler;
import com.example.springcloudapi.dao.entity.Manager;
import com.example.springcloudapi.dao.entity.PolicyMaker;
import com.example.springcloudapi.dao.entity.Public;
import com.example.springcloudapi.mapper.GriddlerMapper;
import com.example.springcloudapi.mapper.ManagerMapper;
import com.example.springcloudapi.mapper.PolicyMakerMapper;
import com.example.springcloudapi.mapper.PublicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private GriddlerMapper griddlerMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private PolicyMakerMapper policyMakerMapper;

    @Autowired
    private PublicMapper publicMapper;
    public Object authenticateUser(String username, String password) {
        System.out.println("authenticateUser");
        QueryWrapper<Griddler> griddlerQuery = new QueryWrapper<>();
        griddlerQuery.eq("phone", username).eq("password", password);
        Griddler griddler = griddlerMapper.selectOne(griddlerQuery);
        if (griddler != null) {
            return new AuthResult("griddler", griddler);
        }

        QueryWrapper<Manager> managerQuery = new QueryWrapper<>();
        managerQuery.eq("manager_code", username).eq("password", password);
        Manager manager = managerMapper.selectOne(managerQuery);
        if (manager != null) {
            return new AuthResult("manager", manager);
        }

        QueryWrapper<PolicyMaker> policyMakerQuery = new QueryWrapper<>();
        policyMakerQuery.eq("username", username).eq("password", password);
        PolicyMaker policyMaker = policyMakerMapper.selectOne(policyMakerQuery);
        System.out.println(policyMaker);
        if (policyMaker != null) {
            System.out.println("----------");
            return new AuthResult("policymaker", policyMaker);
        }

        QueryWrapper<Public> publicQuery = new QueryWrapper<>();
        publicQuery.eq("phone", username).eq("password", password);
        Public publicUser = publicMapper.selectOne(publicQuery);
        if (publicUser != null) {
            return new AuthResult("public", publicUser);
        }

        return null; // 用户未找到或密码不匹配
    }

    public static class AuthResult {
        private String tableName;
        private Object user;

        public AuthResult(String tableName, Object user) {
            this.tableName = tableName;
            this.user = user;
        }

        public String getTableName() {
            return tableName;
        }

        public Object getUser() {
            return user;
        }
    }
}

