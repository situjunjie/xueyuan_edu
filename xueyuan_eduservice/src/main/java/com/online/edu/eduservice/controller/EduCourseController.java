package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.req.form.CourseInfoForm;
import com.online.edu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-03
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
@Api(tags = {"课程信息接口"})
public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoForm form){

        String course_id = eduCourseService.saveCourseInfo(form);

        if(course_id!=null)
            return R.ok().data("courseId",course_id);
        return R.error();
    }

    @PutMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoForm form){
        String course_id = eduCourseService.updateById(form);
        if(course_id!=null)
            return R.ok().data("courseId",course_id);
        return R.error();
    }

    @GetMapping("/getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable("id") String id){

        CourseInfoForm form = eduCourseService.getCourseInfoById(id);
        if(form!=null)
            return R.ok().data("form",form);
        return R.error();
    }

}

