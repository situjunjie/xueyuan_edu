package com.online.edu.eduservice.entity.resp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义用于前端展示树形解构的节点数据解构
 */
@Data
public class TreeNodeVo {

    private String id;

    private String label;

    private List<TreeNodeVo> children = new ArrayList<>();

    private String parentId;
}
