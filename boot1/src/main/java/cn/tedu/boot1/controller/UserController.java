package cn.tedu.boot1.controller;

import cn.tedu.boot1.mapper.UserMapper;
import cn.tedu.boot1.response.ResultVO;
import cn.tedu.boot1.response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users/")
public class UserController {

    @Autowired(required = false)
    UserMapper mapper;

    @RequestMapping("reg")
    public ResultVO reg(){
        return new ResultVO(StatusCode.SUCCESS);
    }

}
