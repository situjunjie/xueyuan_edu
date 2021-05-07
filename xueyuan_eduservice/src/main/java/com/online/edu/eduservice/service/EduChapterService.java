package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.resp.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-06
 */
public interface EduChapterService extends IService<EduChapter> {

    void deleteCourseById(String id);


    //根据课程CourseId查询所有章节和小结
    List<ChapterVo> listChapterVideoByCourseId(String courseId);
}
