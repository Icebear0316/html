package cn.tedu.boot1.controller;

import cn.tedu.boot1.mapper.UserMapper;
import cn.tedu.boot1.pojo.dto.UserLoginDTO;
import cn.tedu.boot1.pojo.dto.UserRegDTO;
import cn.tedu.boot1.pojo.entity.User;
import cn.tedu.boot1.pojo.vo.UserVO;
import cn.tedu.boot1.response.ResultVO;
import cn.tedu.boot1.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/v1/users/")
public class UserController {

    @Autowired(required = false)
    UserMapper mapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("reg")
    public ResultVO reg(@RequestBody UserRegDTO userRegDTO) {
        System.out.println("userRegDTO = " + userRegDTO);
        //判断用户名是否存在
        UserVO userVO = mapper.selectByUsername(userRegDTO.getUsername());
        if (userVO != null) {
            return new ResultVO(StatusCode.USERNAME_ALREADY_EXISTS);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegDTO, user);
        user.setCreated(new Date());
        //对User对象里面的密码进行加密 加密之后会得到一个60个长度的密码
        //修改字段长度(之前是50) alter table weibo change password password varchar(80);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        mapper.insert(user);
        //当给客户端响应的是Java对象时SpringMVC框架会自动将Java对象转成
        //Json格式的字符串,然后将字符串响应给客户端.
        return new ResultVO(StatusCode.SUCCESS);
    }

    @Autowired
    AuthenticationManager manager;
    @RequestMapping("login")
    public ResultVO login(@RequestBody UserLoginDTO userLoginDTO) {
        //通过认证管理器启动Security的认证流程  返回认证结果对象
        Authentication result = manager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUsername(), userLoginDTO.getPassword()));
        //将认证结果保存到Security上下文中   让Security框架记住登录状态
        SecurityContextHolder.getContext().setAuthentication(result);
        //代码执行到这里时代表登录成功!如果登录失败Security框架会抛出异常
        return new ResultVO(StatusCode.SUCCESS);//登录成功
    }
}
