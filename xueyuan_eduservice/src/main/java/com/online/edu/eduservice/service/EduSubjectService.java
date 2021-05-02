package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.resp.TreeNodeVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    List<String> saveFromXls(MultipartFile file);

    List<TreeNodeVo> getAllSubjectTree();

    boolean deleteSubjectById(String id);

    boolean addFirstSubject(EduSubject eduSubject);

    boolean addSecondSubject(EduSubject eduSubject);
}
