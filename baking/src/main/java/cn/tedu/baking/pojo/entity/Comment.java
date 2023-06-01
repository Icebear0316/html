package cn.tedu.baking.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Long id;

    private String content;

    private Long userId;

    private Long contentId;

    private Date createTime;
}