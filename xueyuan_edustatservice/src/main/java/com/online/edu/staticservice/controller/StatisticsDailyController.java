package com.online.edu.staticservice.controller;


import com.netflix.discovery.converters.Auto;
import com.online.edu.common.R;
import com.online.edu.staticservice.client.UcenterClient;
import com.online.edu.staticservice.mapper.StatisticsDailyMapper;
import com.online.edu.staticservice.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sun.misc.UCEncoder;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-15
 */
@CrossOrigin
@RestController
@RequestMapping("/staticservice/statisticsdaily")
@Api
public class StatisticsDailyController {

    @Autowired
    StatisticsDailyService statisticsDailyService;

    @GetMapping("/getStaticsDay/{day}")
    public R getStaticsDay(@PathVariable("day")String day){
         statisticsDailyService.RegistMemberCount(day);

         return  R.ok();


    }

    @GetMapping("/calDailyReport/{type}/{begin}/{end}")
    public R calDailyReport(@PathVariable("type")String type,@PathVariable("begin")String begin,@PathVariable("end")String end){

        Map dataMap = statisticsDailyService.calDailyReport(type,begin,end);
        return R.ok().data("dataMap",dataMap);
    }

}

