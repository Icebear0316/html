package cn.tedu.boot1.controller;


import cn.tedu.boot1.mapper.WeiboMapper;
import cn.tedu.boot1.pojo.dto.WeiboDTO;
import cn.tedu.boot1.pojo.entity.Weibo;
import cn.tedu.boot1.pojo.vo.WeiboIndexVO;
import cn.tedu.boot1.response.ResultVO;
import cn.tedu.boot1.response.StatusCode;
import cn.tedu.boot1.security.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/weibos/")
public class WeiboController {
    @Autowired(required = false)
    WeiboMapper mapper;

    @RequestMapping("insert")
    public ResultVO insert(@RequestBody WeiboDTO weiboDTO,
                           @AuthenticationPrincipal CustomUserDetails ud){
        System.out.println("weiboDTO = " + weiboDTO + ", ud = " + ud);

        Weibo weibo = new Weibo();
        BeanUtils.copyProperties(weiboDTO,weibo);
        //给微博对象设置时间
        weibo.setCreated(new Date());
        //作者id  当前登录的用户id  从Security框架中得到当前登录的用户信息
        weibo.setUserId(ud.getId());
        mapper.insert(weibo);
        return new ResultVO(StatusCode.SUCCESS);
    }

    @RequestMapping("")
    public ResultVO select(){
        System.out.println("请求列表");
        List<WeiboIndexVO> list = mapper.select();
        return new ResultVO(StatusCode.SUCCESS,list);
    }

    @RequestMapping("/{id}/delete")
    public ResultVO delete(@PathVariable Integer id){
        System.out.println("id = " + id);
        mapper.deleteById(id);
        return new ResultVO(StatusCode.SUCCESS);
    }
}
