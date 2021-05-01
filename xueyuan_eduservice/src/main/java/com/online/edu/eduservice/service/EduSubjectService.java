package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-01
 */
public interface EduSubjectService extends IService<EduSubject> {

    //存储xls到edu_subject中
    void saveFromXls(MultipartFile file);
}
