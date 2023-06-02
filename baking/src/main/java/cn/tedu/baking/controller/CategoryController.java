package cn.tedu.baking.controller;


import cn.tedu.baking.mapper.CategoryMapper;
import cn.tedu.baking.response.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/categories/")
public class CategoryController {
    @Autowired
    CategoryMapper mapper;
    /**
     * 根据一级分类的值请求二级分类
     * */
    @RequestMapping("{type}/sub")
    public ResultVO sub(@PathVariable Integer type){

        return ResultVO.ok(mapper.selectByType(type));
    }
}
