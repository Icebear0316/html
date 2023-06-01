package cn.tedu.baking.pojo.vo;

import lombok.Data;




@Data
public class UserVO {
    private Long id;
    private String nickName;
    private String password;
    private Integer isAdmin;
    private String imgUrl;

}
