package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.mapper.EduSubjectMapper;
import com.online.edu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-01
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //存储xls到edu_subject中
    @Override
    public void saveFromXls(MultipartFile file) {

        //1.获取流
        try {
            InputStream in = file.getInputStream();
        //2.获取workbook
            HSSFWorkbook workbook = new HSSFWorkbook(in);
        //3.获取sheet 默认获取第一个sheet
            HSSFSheet sheet = workbook.getSheetAt(0);
            //4.获取行数准备遍历
            int rownum = sheet.getLastRowNum();

            //从第二行开始获取，第一行是标题
            for (int i = 1; i < rownum-1; i++) {
                HSSFRow row = sheet.getRow(i);
                //先处理第一列 一级分类
                HSSFCell cell = row.getCell(0);
                String value1 = cell.getStringCellValue();
                //先测试输出
                //System.out.println(value1);

                if(existsFirstSubject(value1)==null){
                //初始化实体插入数据库
                EduSubject eduSubject = new EduSubject();
                eduSubject.setTitle(value1);
                eduSubject.setParentId("0");
                eduSubject.setSort(0);
                baseMapper.insert(eduSubject);
                }

                //开始处理第二列 二级分类
                HSSFCell cell2 = row.getCell(1);
                String value2 = cell2.getStringCellValue();
                if(existsFirstSubject(value2)==null){
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(value2);
                    eduSubject.setSort(0);
                    QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
                    wrapper.eq("title",value1);
                    String parentid = baseMapper.selectOne(wrapper).getId();
                    System.out.println(baseMapper.selectOne(wrapper));
                    eduSubject.setParentId(parentid);
                    baseMapper.insert(eduSubject);
                }


            }
                in.close();
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    /**
     * 判断一级课程是否已经存在
     * @param name
     * @return
     */
    private EduSubject existsFirstSubject(String name){

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        return baseMapper.selectOne(wrapper);

    }
}
