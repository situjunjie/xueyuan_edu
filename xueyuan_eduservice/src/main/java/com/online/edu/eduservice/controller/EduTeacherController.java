package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.req.QueryTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author situjunjie
 * @since 2021-04-24
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping
    public R getAllTeacherList(){
        //int i =1/0;
        List<EduTeacher> list = eduTeacherService.list(null);
        return  R.ok().data("items",list);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public R deleteTeacherById(@PathVariable String id){
         eduTeacherService.removeById(id);
         return R.ok();
    }

    //分页查询
    @GetMapping("pagelist/{page}/{limit}")
    public R getPageTeacherList(@PathVariable Long page,@PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        eduTeacherService.page(teacherPage,null);
        long total = teacherPage.getTotal();
        List<EduTeacher> items = teacherPage.getRecords();
        return R.ok().data("total",total).data("items",items);
    }

    //分页查询带条件
    @PostMapping("/getMoreConditionPageList/{page}/{limit}")
    public R getMoreConditionPageList(@PathVariable Long page, @PathVariable Long limit,@RequestBody(required = false) QueryTeacher queryTeacher){

        Page<EduTeacher> pageinfo = new Page<EduTeacher>(page,limit);
        eduTeacherService.queryConditionPageList(pageinfo,queryTeacher);
        return R.ok().data("total",pageinfo.getTotal()).data("items",pageinfo.getRecords());
    }

    //新增讲师信息
    @PostMapping("/addTeacherInfo")
    public R addTeacherInfo(@RequestBody EduTeacher teacher){

        boolean b = eduTeacherService.save(teacher);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //根据id获取讲师信息
    @GetMapping("/getTeacherInfo/{id}")
    public R getTeacherInfoById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("items",teacher);
    }

    //更新讲师信息
    @PutMapping("/updateTeacherInfo")
    public R updateTeacherInfo(@RequestBody EduTeacher eduTeacher){

        boolean b = eduTeacherService.updateById(eduTeacher);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }


}

