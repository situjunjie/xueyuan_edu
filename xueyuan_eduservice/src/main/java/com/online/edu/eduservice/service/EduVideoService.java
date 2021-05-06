package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-06
 */
public interface EduVideoService extends IService<EduVideo> {

    void deleteCourseById(String id);
}
