package cn.tedu.baking.pojo.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    private Long id;
    private String content;
    private String nickName;
    private String userImgUrl;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
