package cn.tedu.baking.controller;

import cn.tedu.baking.mapper.BannerMapper;
import cn.tedu.baking.pojo.vo.BannerVO;
import cn.tedu.baking.response.ResultVO;
import cn.tedu.baking.response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/banners/")
public class BannerController {
    @Autowired
    BannerMapper mapper;

    @RequestMapping("")
    public ResultVO select(){

        return ResultVO.ok(mapper.select());
    }


}
