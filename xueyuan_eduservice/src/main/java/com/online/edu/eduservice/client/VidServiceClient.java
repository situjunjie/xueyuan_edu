package com.online.edu.eduservice.client;

import com.online.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("xueyuan-eduvideo")
@Component
public interface VidServiceClient {

    @DeleteMapping("/vidservice/vod/{videoId}")
     R delteAliyunVideoById(@PathVariable("videoId") String videoId);

    @DeleteMapping("/vidservice/vod")
     R delteAliyunVideoByIdList(@RequestParam("ids")List<String> ids);
}
