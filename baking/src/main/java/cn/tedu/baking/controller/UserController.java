package cn.tedu.baking.controller;

import cn.tedu.baking.mapper.UserMapper;
import cn.tedu.baking.pojo.dto.UserRegDTO;
import cn.tedu.baking.response.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    UserMapper mapper;

    @RequestMapping("reg")
    public ResultVO reg(@RequestBody UserRegDTO userRegDTO){
        return ResultVO.ok();
    }
}
