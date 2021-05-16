package com.online.edu.staticservice.client;

import com.online.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("xueyuan-usercenter")
@Component
public interface UcenterClient {

    @GetMapping("/usercenter/ucenter-member/RegisterMemberCount/{date}")
    R getRegisterMemberCount(@PathVariable("date") String date);
}
