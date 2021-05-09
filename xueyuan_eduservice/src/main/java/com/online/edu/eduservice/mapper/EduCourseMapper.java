package com.online.edu.eduservice.mapper;

import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.eduservice.entity.resp.CourseDetailVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-03
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CourseDetailVo getCourseDetailById(String courseId);
}
