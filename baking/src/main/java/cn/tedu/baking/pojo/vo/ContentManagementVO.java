package cn.tedu.baking.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ContentManagementVO {
    private Long id;
    private String title;
    private String imgUrl;
    private String brief;
    private Long type;
    private String categoryName;
    private Integer viewCount;
    private Integer commentCount;
    private Date createTime;
}
