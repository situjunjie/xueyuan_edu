package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.resp.TreeNodeVo;
import com.online.edu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-01
 */
@Api(tags = {"课程分类信息接口"})
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;

    //上传xls文件存储到edu_subject中
    @PostMapping("/uploadXls")
    public R uploadXls(@RequestParam("file") MultipartFile file){

        List<String> msgs = eduSubjectService.saveFromXls(file);
        return R.ok().data("msgs",msgs);
    }

    @GetMapping("/getAllSubjectTree")
    public R getAllSubjectTree(){

        List<TreeNodeVo> subjects = eduSubjectService.getAllSubjectTree();

        return R.ok().data("subjects",subjects);
    }

    @DeleteMapping("/deleteSubjectById/{id}")
    public R deleteSubjectById(@PathVariable("id")String id){

        boolean flag = eduSubjectService.deleteSubjectById(id);
        if(flag)
            return R.ok().message("删除成功");
        return R.error().message("删除失败");
    }


    /**
     * 添加一级分类
     * @param eduSubject
     * @return
     */
    @PostMapping("/addFirstSubject")
    public R addFirstSubject(@RequestBody EduSubject eduSubject){

        boolean flag = eduSubjectService.addFirstSubject(eduSubject);
        if(flag){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 添加二级分类
     * @param eduSubject
     * @return
     */
    @PostMapping("/addSecondSubject")
    public R addSecondSubject(@RequestBody EduSubject eduSubject){

        boolean flag = eduSubjectService.addSecondSubject(eduSubject);
        if(flag){
            return R.ok();
        }
        return R.error();
    }

}

