package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-06
 */
public interface EduChapterService extends IService<EduChapter> {

    void deleteCourseById(String id);
}
