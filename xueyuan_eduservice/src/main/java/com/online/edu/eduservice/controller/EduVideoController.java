package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    EduVideoService eduVideoService;

    @GetMapping("/getVideo/{id}")
    public R getVideoById(@PathVariable String id){
        EduVideo eduVideo = eduVideoService.getById(id);
        return R.ok().data("item",eduVideo);
    }

    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        if (save)
            return R.ok().message("新增小节成功");
        return R.error().message("新增小结失败");
    }

    @PutMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        boolean flag = eduVideoService.updateById(eduVideo);
        if(flag)
            return R.ok().message("修改小节成功");
        return R.error().message("修改小结失败");
    }
    
    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        //TODO 后续需要删除阿里云后台视频
        boolean b = eduVideoService.removeById(id);
        if(b)
            return R.ok().message("删除小节成功");
        return R.error().message("删除小节失败");
    }

}

