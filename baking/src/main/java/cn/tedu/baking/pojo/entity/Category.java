package cn.tedu.baking.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Category {
    private Long id;

    private String name;

    private Integer level;

    private Integer parentId;

    private Integer type;

    private Integer sort;

    private Date createTime;
}