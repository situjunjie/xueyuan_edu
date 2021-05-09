package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.resp.ChapterVo;
import com.online.edu.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class EduChapterController {

    @Autowired
    EduChapterService eduChapterService;

    @GetMapping("/getChapterList/{courseId}")
    public R getChapterListByCourseId(@PathVariable String courseId){

        List<ChapterVo> list = eduChapterService.listChapterVideoByCourseId(courseId);
        return R.ok().data("items",list);
    }

    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter chapter){

        boolean save = eduChapterService.save(chapter);
        if(save)
            return R.ok().message("添加章节成功");
        return R.error();
    }
    
    @PutMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter){
        boolean flag = eduChapterService.updateById(chapter);
        if(flag)
            return R.ok().message("章节修改成功");
        return R.error();
    }

    @DeleteMapping("/deleteChapterById/{id}")
    public R deleteChapterById(@PathVariable String id){
        boolean flag = eduChapterService.removeChapterById(id);
        if(flag)
            return R.ok().message("章节删除成功");
        return R.error().message("章节删除失败");
    }



}

