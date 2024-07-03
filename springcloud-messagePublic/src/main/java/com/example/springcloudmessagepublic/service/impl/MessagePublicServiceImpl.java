package com.example.springcloudmessagepublic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudapi.dao.dto.MessagePublicDTO;
import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudapi.dao.entity.Province;
import com.example.springcloudapi.dao.entity.Public;
import com.example.springcloudmessagepublic.feign.CitiesFeignService;
import com.example.springcloudmessagepublic.feign.PublicFeignService;
import com.example.springcloudmessagepublic.mapper.MessagePublicMapper;
import com.example.springcloudmessagepublic.service.MessagePubilcService;
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
 * @Date 2024/7/3 10:15
 * @Version 1.0
 */
@Service
@Accessors(chain = true)
public class MessagePublicServiceImpl extends ServiceImpl<MessagePublicMapper,MessagePublic>implements MessagePubilcService {
    @Autowired
    private MessagePublicMapper messagePublicMapper;

    @Autowired
    private PublicFeignService publicFeignService;

    @Autowired
    private CitiesFeignService citiesFeignService;

    public Map<String, Object> getPaginatedMessagePublics(int current, int size, QueryWrapper<MessagePublic> queryWrapper) {
        Map<String, Object> response = new HashMap<>();
        ArrayList<MessagePublicDTO> messagePublicDTOs = new ArrayList<>();
        Page<MessagePublic> page = new Page<>(current, size);

        Page<MessagePublic> messagePublicPage = messagePublicMapper.selectPage(page, queryWrapper);

        // 打印分页查询结果
        System.out.println("Page: " + messagePublicPage);
        List<MessagePublic> messagePublics = messagePublicPage.getRecords();

        System.out.println("Current: " + current);
        System.out.println("Size: " + size);
        System.out.println("Total Records: " + messagePublicPage.getTotal());
        System.out.println("Page Records: " + messagePublics.size());

        for (MessagePublic messagePublic : messagePublics) {
            String publicId = messagePublic.getPublicId();
            Public publicDetail = publicFeignService.getPublicById(publicId);
            String city = citiesFeignService.selectCityName(messagePublic.getCityId()).toString();
            Object data1 = citiesFeignService.selectProvince(messagePublic.getProvinceId()).get("data");
            ObjectMapper objectMapper1 = new ObjectMapper();
            Province province = objectMapper1.convertValue(data1, Province.class);

            MessagePublicDTO messagePublicDTO = new MessagePublicDTO(
                    publicDetail, messagePublic, province.getProvinceName(),
                    city, province.getShortTitle()
            );
            messagePublicDTOs.add(messagePublicDTO);
        }

        if (!messagePublicDTOs.isEmpty()) {
            response.put("success", true);
            response.put("message", "查询所有的公众监督员的提交记录分页成功");
            response.put("data", messagePublicDTOs);
            response.put("result",messagePublicPage.getTotal());
        } else {
            response.put("success", false);
            response.put("message", "当前用户还未提交过反馈信息");
            response.put("data", null);
        }

        return response;
    }
}

