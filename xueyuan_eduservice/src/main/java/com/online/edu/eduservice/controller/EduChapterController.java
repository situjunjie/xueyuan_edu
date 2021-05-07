package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.resp.ChapterVo;
import com.online.edu.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-06
 */
@Api(tags = {"章节信息接口"})
@RestController
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {

    @Autowired
    EduChapterService eduChapterService;

    @GetMapping("/getChapterList/{courseId}")
    public R getChapterListByCourseId(@PathVariable String courseId){

        List<ChapterVo> list = eduChapterService.listChapterVideoByCourseId(courseId);
        return R.ok().data("items",list);
    }

}

