package cn.tedu.boot1.controller;


import cn.tedu.boot1.mapper.WeiboMapper;
import cn.tedu.boot1.pojo.dto.WeiboDTO;
import cn.tedu.boot1.pojo.entity.Weibo;
import cn.tedu.boot1.response.ResultVO;
import cn.tedu.boot1.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;

@RestController
@RequestMapping("/v1/weibos/")
public class WeiboController {
    @Autowired(required = false)
    WeiboMapper mapper;

    @RequestMapping("insert")
    public ResultVO insert(@RequestBody WeiboDTO weiboDTO,
                           @AuthenticationPrincipal UserDetails ud){
        System.out.println("weiboDTO = " + weiboDTO + ", ud = " + ud);





        Weibo weibo = new Weibo();
        BeanUtils.copyProperties(weiboDTO,weibo);

        weibo.setCreated(new Date());

        mapper.insert(weibo);
        return new ResultVO(StatusCode.SUCCESS);
    }
}
