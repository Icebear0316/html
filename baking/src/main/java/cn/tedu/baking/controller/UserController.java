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
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    UserMapper mapper;

    @Autowired
    PasswordEncoder passwordEncoder;

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
            //密码加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            mapper.insert(user);
            return ResultVO.ok();
    }

    @Autowired
    AuthenticationManager manager;


    @RequestMapping("login")
    public ResultVO login(@RequestBody UserLoginDTO userLoginDTO){
        //通过认证管理器启动Security的认证流程  返回认证结果对象
        Authentication result = manager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUserName(), userLoginDTO.getPassword()));
        //将认证结果保存到Security上下文中   让Security框架记住登录状态
        SecurityContextHolder.getContext().setAuthentication(result);
        //代码执行到这里时代表登录成功!如果登录失败Security框架会抛出异常

        //result.getPrincipal()得到登陆成功的CustomUserDetails
        return ResultVO.ok(result.getPrincipal());
    }

    @RequestMapping("logout")
    public void logout(){
        //从Security框架中删除认证数据
        SecurityContextHolder.clearContext();
    }
}
