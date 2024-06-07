package com.example.neps.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.neps.dao.entity.Message;
import com.example.neps.mapper.MessageMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/7 9:29
 * @Version 1.0
 */
@Controller
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageMapper messageMapper;
    @GetMapping("/createMessage")
    public Map<String, Object> creatMessage(@RequestParam("province") String province,
                                            @RequestParam("city") String city,
                                            @RequestParam("address") String address,
                                            @RequestParam("level") String level,
                                            @RequestParam("detail")String detail,
                                            @RequestParam("userid") String userid) throws ParseException {
        Map<String,Object> response = new HashMap<>();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        Date date = dateFormat.parse(formattedDate);

        String id = UUID.randomUUID().toString();
        Message message = new Message(id, userid, province, city, level, date, detail,address);
        int i = messageMapper.insert(message);
        if(i == 0){

            response.put("success:",false);
            response.put("data:",null);
            response.put("message","提交空气质量监督信息失败");
        }else {
            response.put("success:",true);
            response.put("data:",message);
            response.put("message","提交空气质量监督信息成功");
        }
        return response;
    }
    @RequestMapping("/viewMyMessage")
    public Map<String, Object> viewMyMessage(@RequestParam("userId") String userId){
        Map<String,Object> response = new HashMap<>();
        List<Message> list = messageMapper.selectList(Wrappers.<Message>lambdaQuery().eq(Message::getUserId, userId));
        if(list.isEmpty()){
            response.put("success:",false);
            response.put("data:",null);
            response.put("message","暂无任何提交记录");
        }else {
            response.put("success:",true);
            response.put("data:",list);
            response.put("message","查询当前用户的所有提交记录成功");
//            Runtimelogger.info("FileController.getTaskChildPhoto:获取作业图片成功");
        }
        return response;
    }

}
