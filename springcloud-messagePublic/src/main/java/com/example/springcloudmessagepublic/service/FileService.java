package com.example.springcloudmessagepublic.service;

import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudmessagepublic.mapper.MessagePublicMapper;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MessagePublicMapper messagePublicMapper;


    private String minioBucketName = "nep";

    public int uploadImageToMinioAndSaveToDb(String publicId, String provinceId, String cityId, String address, int level, String description, Date date, int status,MultipartFile file) {
        try {
            MessagePublic messagePublic = new MessagePublic(UUID.randomUUID().toString(), publicId, provinceId, cityId, address, level, description, date, status);
            // 生成一个唯一的文件名，例如使用UUID
            String objectName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            // 上传文件到Minio
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioBucketName)
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            System.out.println(objectName);

            // 构建存储在数据库中的完整图片URL
            String imageUrl = "http://192.168.40.129:19000/nep/"+objectName;
            System.out.println(imageUrl);
            messagePublic.setPhoto(imageUrl);
            int insert = messagePublicMapper.insert(messagePublic);

            return insert;

        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            // 处理异常
            return 0;
        }
    }
}
