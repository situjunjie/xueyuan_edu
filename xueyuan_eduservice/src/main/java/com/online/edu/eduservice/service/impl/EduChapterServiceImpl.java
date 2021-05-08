package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.entity.resp.ChapterVo;
import com.online.edu.eduservice.entity.resp.VideoVo;
import com.online.edu.eduservice.mapper.EduChapterMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-06
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService eduVideoService;

    @Override
    public void deleteCourseById(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<ChapterVo> listChapterVideoByCourseId(String courseId) {
        List<ChapterVo> list = new ArrayList<>();
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> chapters = baseMapper.selectList(wrapper);
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(wrapper1);

        for (EduChapter chapter: chapters
             ) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            for(EduVideo eduVideo:eduVideos){
                if(eduVideo.getChapterId().equals(chapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    chapterVo.getChildren().add(videoVo);
                }
            }
            list.add(chapterVo);
        }
        return list;

//        for(EduVideo eduVideo:eduVideos){
//            VideoVo videoVo = new VideoVo();
//            BeanUtils.copyProperties(eduVideo,videoVo);
//            for(ChapterVo chapterVo:list){
//                if(eduVideo.getCourseId())
//            }
//        }


    }

    @Override
    public boolean removeChapterById(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        int count = eduVideoService.count(wrapper);
        if(count>0)
            return false;
        baseMapper.deleteById(id);
        return true;
    }
}
