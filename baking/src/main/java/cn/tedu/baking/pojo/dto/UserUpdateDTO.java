package cn.tedu.baking.pojo.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private long id;
    private String nickName;
    private String imgUrl;
}
