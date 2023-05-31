package cn.tedu.boot1.mapper;

import cn.tedu.boot1.pojo.entity.User;
import cn.tedu.boot1.pojo.vo.UserVO;

public interface UserMapper {

    int insert(User user);

    UserVO selectByUsername(String username);
}
