package com.online.edu.eduservice.service.impl;

import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.req.form.CourseInfoForm;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-03
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;


    @Override
    @Transactional
    public String saveCourseInfo(CourseInfoForm form) {

        //1.第一步保存到edu_course
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(form,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        //判断第一步是否成功在进行第二步
        if(insert==0)
            throw new RuntimeException("保存失败");
        //如果无异常再进行第二部 保存课程描述
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(form,eduCourseDescription);
        eduCourseDescription.setId(eduCourse.getId());
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if(save)
            return eduCourse.getId();
        return null;
    }

    @Override
    @Transactional
    public String updateById(CourseInfoForm form) {
        //1.第一步保存到edu_course
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(form,eduCourse);
        int flag = baseMapper.updateById(eduCourse);

        //判断第一步是否成功在进行第二步
        if(flag==0)
            throw new RuntimeException("保存失败");
        //如果无异常再进行第二部 保存课程描述
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(form,eduCourseDescription);
        boolean flag2 = eduCourseDescriptionService.updateById(eduCourseDescription);
        if(flag2)
            return eduCourse.getId();
        return null;
    }

    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        CourseInfoForm form = new CourseInfoForm();
        EduCourse eduCourse = baseMapper.selectById(id);
        BeanUtils.copyProperties(eduCourse,form);
        EduCourseDescription description = eduCourseDescriptionService.getById(id);
        BeanUtils.copyProperties(description,form);
        return form;
    }
}
