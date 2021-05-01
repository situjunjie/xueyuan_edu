package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;

    //上传xls文件存储到edu_subject中
    @PostMapping("/uploadXls")
    public R uploadXls(@RequestParam("file") MultipartFile file){

        eduSubjectService.saveFromXls(file);
        return R.ok();
    }
}

