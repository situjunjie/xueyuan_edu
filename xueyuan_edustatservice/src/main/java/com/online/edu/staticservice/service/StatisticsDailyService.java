package com.online.edu.staticservice.service;

import com.online.edu.staticservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-15
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void RegistMemberCount(String day);

    Map calDailyReport(String type, String begin, String end);
}
