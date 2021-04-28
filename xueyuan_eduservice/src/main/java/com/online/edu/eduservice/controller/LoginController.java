package com.online.edu.eduservice.controller;

import com.online.edu.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(description="模拟用户登陆")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {

    ////{"code":2000e,"data":{"token":"admin"}}
    @ApiOperation("模拟登陆")
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //{"code":20000,"data":{"roles":["admin"],"name":"admin","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
    @ApiOperation(value="模拟获取信息")
    @GetMapping("info")
    public R info(){

        return R.ok().data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

    }

}