package com.online.edu.xueyuaneduvideo.controller;

import com.online.edu.common.R;
import com.online.edu.xueyuaneduvideo.service.VidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/vidservice/vod")
@CrossOrigin
public class VidController {

    @Autowired
    VidService vidService;

    @PostMapping("/uploadVideo")
    public R uploadVideo(MultipartFile file) throws IOException {
        String vid = vidService.uploadVideoAli(file);
        return R.ok().data("videoId",vid);
    }

    @DeleteMapping("/{videoId}")
    public R delteAliyunVideoById(@PathVariable String videoId){
        boolean flag = vidService.delteAliyunVideoById(videoId);
        return R.ok().message("删除视频成功");
    }
}
