package com.online.edu.staticservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.common.R;
import com.online.edu.staticservice.client.UcenterClient;
import com.online.edu.staticservice.entity.StatisticsDaily;
import com.online.edu.staticservice.mapper.StatisticsDailyMapper;
import com.online.edu.staticservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-15
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    UcenterClient ucenterClient;



    @Override
    public void RegistMemberCount(String day) {
        //新增记录前，如果之前有数据则直接删除
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        //开始新增数据：
        R r = ucenterClient.getRegisterMemberCount(day);

        //获取统计信息
        Integer registerNum = (Integer) r.getData().get("registerCount");
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO

        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);

    }

    @Override
    public Map calDailyReport(String type, String begin, String end) {
        HashMap<String,Object> dataMap = new HashMap<>();
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        List<StatisticsDaily> rs = baseMapper.selectList(wrapper);
        List<String> date = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (StatisticsDaily r : rs) {
            date.add(r.getDateCalculated());
            switch (type){
                case "login_num":
                    result.add(r.getLoginNum());
                    break;
                case "register_num":
                    result.add(r.getRegisterNum());
                    break;
                case "video_view_num":
                    result.add(r.getVideoViewNum());
                    break;
                case "course_num":
                    result.add(r.getCourseNum());
                    break;
            }
        }
        dataMap.put("date",date);
        dataMap.put("result",result);
        return dataMap;
    }
}
