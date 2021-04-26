package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.req.QueryTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author situjunjie
 * @since 2021-04-24
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void queryConditionPageList(Page<EduTeacher> pageinfo, QueryTeacher queryTeacher);
}
