package com.online.edu.eduservice.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.online.edu.common.R;
import com.online.edu.eduservice.handler.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

@Api(tags = {"文件上传接口"})
@RestController
@CrossOrigin
@RequestMapping("/eduservice/oss")
public class FileUploadController {

    @PostMapping("/uploadImg")
    public R uploadImg(@RequestParam("file") MultipartFile file){
        try {
            String filename = new DateTime().toString("yyyy/MM/dd")+"/"+UUID.randomUUID().toString()+file.getOriginalFilename();
            InputStream in = file.getInputStream();
            String bucketName = ConstantPropertiesUtil.BUCKETNAME;
            // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtil.ENDPOINT;
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtil.KEYID;
        String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, filename, in);
        String path = "https://"+bucketName+"."+endpoint+"/"+filename;


// 关闭OSSClient。
        ossClient.shutdown();

        return R.ok().data("imgurl",path);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
