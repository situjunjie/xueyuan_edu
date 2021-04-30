package com.online.edu.eduservice.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {


    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyid;
    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        KEYID = keyid;
        KEYSECRET =keysecret;
        BUCKETNAME = bucketname;
    }
}
