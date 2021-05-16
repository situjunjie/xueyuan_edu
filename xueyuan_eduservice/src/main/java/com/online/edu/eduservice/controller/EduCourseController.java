package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.client.VidServiceClient;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.entity.req.CourseQuery;
import com.online.edu.eduservice.entity.req.form.CourseInfoForm;
import com.online.edu.eduservice.entity.resp.CourseDetailVo;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.online.edu.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    EduChapterService eduChapterService;

    @Autowired
    EduVideoService eduVideoService;

    @Autowired
    VidServiceClient vidServiceClient;

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

    @GetMapping("/getAllCourses")
    public R getAllCourse(){
        List<EduCourse> list = eduCourseService.getAllCourse();
        return R.ok().data("items",list);
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public R deleteCourseByCourseId(@PathVariable String courseId){

        //1.删除所有小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(wrapper);
        List<String> videoSourceIds = new ArrayList<>();
        for (EduVideo video:eduVideoList
             ) {
            videoSourceIds.add(video.getVideoSourceId());
        }
        vidServiceClient.delteAliyunVideoByIdList(videoSourceIds);
        eduVideoService.remove(wrapper);
        //2.删除所有章节
        QueryWrapper<EduChapter> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        eduChapterService.remove(wrapper1);
        //3.删除课程
        eduCourseService.removeById(courseId);

        //4.删除课程描述
        eduCourseDescriptionService.removeById(courseId);
        return R.ok();
    }

    /**
     * 分页带条件查询课程信息
     * @param page
     * @param limit
     * @param query
     * @return
     */
    @PostMapping("/course-list/{page}/{limit}")
    public R getCoursePage(@PathVariable("page") long page, @PathVariable("limit") long limit, @RequestBody(required = false) CourseQuery query){
        Page<EduCourse> pageInfo = new Page<>(page,limit);
        if(query==null){
            IPage<EduCourse> list = eduCourseService.page(pageInfo, null);
            return R.ok().data("items",list);
        }
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(query.getSubjectId()))
            wrapper.eq("subject_id",query.getSubjectId());
        if(!StringUtils.isEmpty(query.getFirstSubjectId()))
            wrapper.eq("first_subject_id",query.getFirstSubjectId());
        if(!StringUtils.isEmpty(query.getTeacherId()))
            wrapper.eq("teacher_id",query.getTeacherId());
        if(!StringUtils.isEmpty(query.getTitle()))
            wrapper.like("title",query.getTitle());
        IPage<EduCourse> list = eduCourseService.page(pageInfo, wrapper);
        return R.ok().data("items",list);
    }

    @DeleteMapping("/delete-course/{id}")
    public R removeCourseById(@PathVariable("id") String id){

        eduVideoService.deleteCourseById(id);

        eduChapterService.deleteCourseById(id);

        eduCourseDescriptionService.deleteCourseById(id);

        eduCourseService.removeById(id);

        return R.ok();
    }

    @GetMapping("/getCourseDetail/{courseId}")
    public R getCourseDetailById(@PathVariable String courseId){
        CourseDetailVo vo = eduCourseService.getCourseDetailById(courseId);
        return R.ok().data("courseDetail",vo);
    }

    @GetMapping("/updateCoursePublish/{courseId}")
    public R updateCoursePublish(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }
}

