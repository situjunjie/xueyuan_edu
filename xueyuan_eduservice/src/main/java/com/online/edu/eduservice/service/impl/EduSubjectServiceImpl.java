package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.resp.TreeNodeVo;
import com.online.edu.eduservice.mapper.EduSubjectMapper;
import com.online.edu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    public List<String> saveFromXls(MultipartFile file) {

        List<String> msgs = new ArrayList<>();
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
                if(row==null){
                    msgs.add("第"+(i+1)+"行为空，请检查");
                    continue;
                }
                //先处理第一列 一级分类
                HSSFCell cell = row.getCell(0);
                if(cell==null){
                    msgs.add("第"+(i+1)+"行为空，请检查");
                    continue;
                }
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
                if(cell2==null){
                    msgs.add("第"+(i+1)+"行为空，请检查");
                    continue;
                }
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
        return msgs;


    }

    @Override
    public List<TreeNodeVo> getAllSubjectTree() {
        //获取所有的课程信息
        List<EduSubject> eduSubjects = baseMapper.selectList(null);
        List<TreeNodeVo> subjectTree = new ArrayList<>();
        //遍历整理课程信息对象到树节点对象,第一个for处理二级节点
        for(EduSubject subject:eduSubjects){
            if(subject.getParentId().equals("0"))
                continue;
            TreeNodeVo node = new TreeNodeVo();
            node.setId(subject.getId());
            node.setLabel(subject.getTitle());
            node.setParentId(subject.getParentId());
            subjectTree.add(node);
        }
        //遍历处理，处理父节点
        for(EduSubject subject:eduSubjects){
            //不是父节点直接下一次循环
            if(!subject.getParentId().equals("0"))
                continue;
            TreeNodeVo node = new TreeNodeVo();
            //遍历添加子节点
            for(TreeNodeVo obj2 :subjectTree){
                if(obj2.getParentId().equals(subject.getId()))
                    node.getChildren().add(obj2);
            }
            node.setId(subject.getId());
            node.setLabel(subject.getTitle());
            node.setParentId(subject.getParentId());
            subjectTree.add(node);
        }

        return subjectTree.stream().filter(x -> x.getParentId().equals("0")).collect(Collectors.toList());

    }

    @Override
    public boolean deleteSubjectById(String id) {
        EduSubject eduSubject = baseMapper.selectById(id);
        //如果是一级课程，必须子课程为空才能进行删除 否则返回false
        if(eduSubject.getParentId().equals("0")){
            QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
            wrapper.eq("parent_id",id);
            Integer count = baseMapper.selectCount(wrapper);
            if(count!=0) {
                return false;
            }
            baseMapper.deleteById(id);
            return true;
        }
        //如果不是一级课程直接删除
        baseMapper.deleteById(id);
        return true;
    }

    /**
     * 添加一级分类
     * @param eduSubject
     * @return
     */
    @Override
    public boolean addFirstSubject(EduSubject eduSubject) {
        eduSubject.setParentId("0");
        if(existsFirstSubject(eduSubject.getTitle())==null){
            baseMapper.insert(eduSubject);
            return true;
        }
        return false;
    }

    /**
     * 添加二级分类
     * @param eduSubject
     * @return
     */
    @Override
    public boolean addSecondSubject(EduSubject eduSubject) {
        if(existsSeconSubject(eduSubject.getTitle(), eduSubject.getParentId())==null){
            baseMapper.insert(eduSubject);
            return true;
        }
        return false;
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

    /**
     * 判断二级课程是否已经存在
     * @param name
     * @return
     */
    private EduSubject existsSeconSubject(String name,String parantId){

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",parantId);
        return baseMapper.selectOne(wrapper);

    }
}
