package com.online.edu.xueyuaneduvideo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VidService {
    String uploadVideoAli(MultipartFile file) throws IOException;

    boolean delteAliyunVideoById(String videoId);
}
