package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.req.form.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-03
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoForm form);

    String updateById(CourseInfoForm form);

    CourseInfoForm getCourseInfoById(String id);
}
