package com.example.springcloudmessagegriddler.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudapi.dao.entity.MessageGriddler;
import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudapi.dao.dto.MessageGriddlerViewDTO;
import com.example.springcloudmessagegriddler.feign.GriddlerFeignService;
import com.example.springcloudmessagegriddler.feign.MessagePublicFeignService;
import com.example.springcloudmessagegriddler.mapper.MessageGriddlerMapper;
import com.example.springcloudmessagegriddler.service.MessageGriddlerService;
import com.example.springcloudapi.dao.dto.MessagePublicPageDTO;
//import com.example.springcloudmessagepublic.service.MessagePubilcService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/7 9:26
 * @Version 1.0
 */
@Service
@Accessors(chain = true)
public class MessageGriddlerServiceImpl extends ServiceImpl<MessageGriddlerMapper, MessageGriddler>implements MessageGriddlerService {
    @Autowired
    MessageGriddlerMapper messageGriddlerMapper;
    @Autowired
    MessagePublicFeignService messagePublicFeignService;
    @Autowired
    GriddlerFeignService griddlerFeignService;


//    @Override
//    public Map<String, Object> getPaginatedMessageGriddlers(int current, int size, QueryWrapper<MessageGriddler> queryWrapper) {
//        List<MessageGriddlerViewDTO> messageGriddlerViewDTOList = new ArrayList<>();
//        Page<MessageGriddler> page = new Page<>(current, size);
//
//        // Perform paginated query
//        Page<MessageGriddler> messageGriddlerPage = messageGriddlerMapper.selectPage(page, queryWrapper);
//        List<MessageGriddler> messageGriddlers = messageGriddlerPage.getRecords();
//
//        System.out.println("Current: " + current);
//        System.out.println("Size: " + size);
//        System.out.println("Total Records: " + messageGriddlerPage.getTotal());
//        System.out.println("Page Records: " + messageGriddlers.size());
//
//        for (MessageGriddler messageGriddler : messageGriddlers) {
//            Object data = messagePublicFeignService.selectMessagePublic(messageGriddler.getMessagePublicId()).get("data");
//            ObjectMapper objectMapper = new ObjectMapper();
//            MessagePublicPageDTO messagePublicPageDTO = objectMapper.convertValue(data, MessagePublicPageDTO.class);
//            String publicName = messagePublicPageDTO.getAPublic().getName();
//            String griddlerName = griddlerFeignService.selectGriddlerName(messageGriddler.getGriddlerId());
//            MessageGriddlerViewDTO messageGriddlerViewDTO = new MessageGriddlerViewDTO(
//                    messageGriddler,
//                    messagePublicPageDTO.getMessagePublic().getProvinceId(),
//                    messagePublicPageDTO.getMessagePublic().getCityId(),
//                    griddlerName,
//                    publicName
//            );
//            messageGriddlerViewDTOList.add(messageGriddlerViewDTO);
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        if (!messageGriddlers.isEmpty()) {
//            response.put("success", true);
//            response.put("message", "查看所有的网格员端的提交实测数据成功");
//            response.put("data", messageGriddlerViewDTOList);
//        } else {
//            response.put("success", false);
//            response.put("message", "查看所有的网格员端的提交实测数据失败");
//            response.put("data", null);
//        }
//        return response;
//    }
    @Override
    public Map<String, Object> getPaginatedMessageGriddlers(int current, int size, QueryWrapper<MessageGriddler> queryWrapper) {
        List<MessageGriddlerViewDTO> messageGriddlerViewDTOList = new ArrayList<>();
        Page<MessageGriddler> page = new Page<>(current, size);

        // Perform paginated query
        Page<MessageGriddler> messageGriddlerPage = messageGriddlerMapper.selectPage(page, queryWrapper);
        List<MessageGriddler> messageGriddlers = messageGriddlerPage.getRecords();

        System.out.println("Current: " + current);
        System.out.println("Size: " + size);
        System.out.println("Total Records: " + messageGriddlerPage.getTotal());
        System.out.println("Page Records: " + messageGriddlers.size());

        for (MessageGriddler messageGriddler : messageGriddlers) {
            Object data = messagePublicFeignService.selectMessagePublic(messageGriddler.getMessagePublicId()).get("data");
            ObjectMapper objectMapper = new ObjectMapper();
            MessagePublicPageDTO messagePublicPageDTO = objectMapper.convertValue(data, MessagePublicPageDTO.class);
            String publicName = messagePublicPageDTO.getAPublic().getName();
            String griddlerName = griddlerFeignService.selectGriddlerName(messageGriddler.getGriddlerId());
            MessageGriddlerViewDTO messageGriddlerViewDTO = new MessageGriddlerViewDTO(
                    messageGriddler,
                    messagePublicPageDTO.getMessagePublic().getProvinceId(),
                    messagePublicPageDTO.getMessagePublic().getCityId(),
                    griddlerName,
                    publicName
            );
            messageGriddlerViewDTOList.add(messageGriddlerViewDTO);
        }

        Map<String, Object> response = new HashMap<>();
        if (!messageGriddlers.isEmpty()) {
            response.put("success", true);
            response.put("message", "查看所有的网格员端的提交实测数据成功");
            response.put("data", messageGriddlerViewDTOList);
            response.put("result",messageGriddlerPage.getTotal());
        } else {
            response.put("success", false);
            response.put("message", "查看所有的网格员端的提交实测数据失败");
            response.put("data", null);
            response.put("result",null);
        }
        return response;
    }

}
