package cn.tedu.baking.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class ContentDetailVO {
    private Long id;
    private String title;
    private String brief;
    private String videoUrl;
    private String imgUrl;
    private String content;
    private Integer type;
    private Integer viewCount;
    private Integer commentCount;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private String nickName;
    private String userImgUrl;
    private Long userId;

}
