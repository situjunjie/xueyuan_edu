package com.online.edu.usercenter.controller;


import com.netflix.discovery.converters.Auto;
import com.online.edu.common.R;
import com.online.edu.usercenter.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-15
 */
@CrossOrigin
@RestController
@RequestMapping("/usercenter/ucenter-member")
@Api
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/RegisterMemberCount/{date}")
    public R getRegisterMemberCount(@PathVariable("date") String date){
        Integer count = memberService.getRegisterMemberCount(date);
        return R.ok().data("registerCount",count);
    }

}

