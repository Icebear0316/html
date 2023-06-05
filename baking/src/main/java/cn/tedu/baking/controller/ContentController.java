package cn.tedu.baking.controller;


import cn.tedu.baking.mapper.ContentMapper;
import cn.tedu.baking.pojo.dto.ContentDTO;
import cn.tedu.baking.pojo.entity.Content;
import cn.tedu.baking.pojo.vo.ContentManagementVO;
import cn.tedu.baking.response.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/contents/")
public class ContentController {
    @Autowired
    ContentMapper mapper;

    /**
     * http://localhost:8088/v1/contents/add-new
     * @return
     * */
    @RequestMapping("add-new")
    public ResultVO addNew(@RequestBody ContentDTO contentDTO){
        System.out.println("contentDTO = " + contentDTO);
        Content content = new Content();
        BeanUtils.copyProperties(contentDTO,content);
        content.setCreateTime(new Date());

        mapper.insert(content);
        return ResultVO.ok();
    }

    @RequestMapping("/{type}/management")
    public ResultVO management(@PathVariable Integer type){
        List<ContentManagementVO> List = mapper.selectByType(type);
        return ResultVO.ok();
    }
}
