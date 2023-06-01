package cn.tedu.baking.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Content {
    private Long id;

    private String title;

    private String imgUrl;

    private String videoUrl;

    private String brief;

    private Long type;

    private Long categoryId;

    private Integer viewCount;

    private Integer commentCount;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private String content;

}