package com.online.edu.eduservice.entity.resp;

import lombok.Data;

/**
 * 用于封装课程详细信息的Vo
 */
@Data
public class CourseDetailVo {
    private String id;
    private String title;
    private String price;
    private String description;
    private String teacherName;
    private String firstSubject;
    private String secondSubject;
    private String cover;
}
