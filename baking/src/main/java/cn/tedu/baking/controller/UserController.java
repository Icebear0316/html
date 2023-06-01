package cn.tedu.baking.controller;

import cn.tedu.baking.mapper.UserMapper;
import cn.tedu.baking.pojo.dto.UserLoginDTO;
import cn.tedu.baking.pojo.dto.UserRegDTO;
import cn.tedu.baking.pojo.entity.User;
import cn.tedu.baking.pojo.vo.UserVO;
import cn.tedu.baking.response.ResultVO;
import cn.tedu.baking.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    UserMapper mapper;

    @RequestMapping("reg")
    public ResultVO reg(@RequestBody UserRegDTO userRegDTO){
        UserVO userVO = mapper.selectByUserName(userRegDTO.getUserName());
        if(userVO!=null){
            return new ResultVO(StatusCode.USERNAME_ALREADY_EXISTS);
        }
            User user = new User();
            BeanUtils.copyProperties(userRegDTO,user);
            user.setCreateTime(new Date());
            user.setIsAdmin(0);//默认不是管理员
            user.setImgUrl("/imgs/icon.png");
            mapper.insert(user);
            return ResultVO.ok();
    }

    @RequestMapping("login")
    public ResultVO login(@RequestBody UserLoginDTO userLoginDTO){
        return ResultVO.ok();
    }
}
