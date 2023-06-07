package cn.tedu.baking.controller;


import cn.tedu.baking.mapper.ContentMapper;
import cn.tedu.baking.pojo.dto.ContentDTO;
import cn.tedu.baking.pojo.entity.Content;
import cn.tedu.baking.pojo.vo.*;
import cn.tedu.baking.response.ResultVO;
import cn.tedu.baking.response.StatusCode;
import cn.tedu.baking.security.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/contents/")
public class ContentController {
    @Value("${filePath}")
    private String filePath;

    @Autowired
    ContentMapper mapper;
    /**
     *  http://localhost:8080/v1/contents/add-new
     * @return
     */
    @RequestMapping("add-new")
    public ResultVO addNew(@RequestBody ContentDTO contentDTO,
                           @AuthenticationPrincipal CustomUserDetails userDetails){
        System.out.println("contentDTO = " + contentDTO);
        Content content = new Content();
        BeanUtils.copyProperties(contentDTO,content);
        if (contentDTO.getId()==null){//添加
            content.setCreateTime(new Date());
            mapper.insert(content);
        }else{//修改
            content.setUpdateTime(new Date());
            content.setUpdateBy(userDetails.getId());//设置修改人为当前登录的用户
            mapper.update(content);
        }

        return ResultVO.ok();
    }

    @RequestMapping("/{type}/management")
    public ResultVO management(@PathVariable Integer type,
                               @AuthenticationPrincipal CustomUserDetails userDetails){
        System.out.println("type = " + type + ", userDetails = " + userDetails);
        //判断是否登录了
        if (userDetails==null){
            return new ResultVO(StatusCode.NOT_LOGIN);
        }

        List<ContentManagementVO> list =
                mapper.selectByType(type,userDetails.getId());

        return ResultVO.ok(list);
    }
    @RequestMapping("/{id}/edit")
    public ResultVO getEdit(@PathVariable Long id){

        ContentEditVO contentEditVO = mapper.selectByIdForEdit(id);
        return ResultVO.ok(contentEditVO);
    }

    @RequestMapping("/{id}/delete")
    public ResultVO delete(@PathVariable Long id){
        //得到封面的图片路径 然后删除文件
        ContentEditVO contentEditVO = mapper.selectByIdForEdit(id);
        new File(filePath+contentEditVO.getImgUrl()).delete();
        //如果内容为视频类型 则得到视频路径并删除
        if (contentEditVO.getType()==2){
            new File(filePath+contentEditVO.getVideoUrl()).delete();
        }
        //删除数据库里面的数据
        mapper.deleteById(id);
        return ResultVO.ok();

    }


    @RequestMapping("/{type}/{categoryId}/index")
    public ResultVO index(@PathVariable Integer type,
                          @PathVariable Long categoryId){
        List<ContentIndexVO> list =
                mapper.selectByTypeAndCategoryId(type,categoryId);

        return ResultVO.ok(list);
    }

    @RequestMapping("/{type}/list")
    public ResultVO list(@PathVariable Integer type){
        List<ContentIndexVO> list = mapper.selectByTypeForList(type);
        return ResultVO.ok(list);

    }

    @RequestMapping("/{id}/detail")
    public ResultVO detail(@PathVariable Long id){
        //通过id查询详情,代表浏览了一次
        mapper.updateViewCountById(id);

        ContentDetailVO contentDetailVO = mapper.selectByIdForDetail(id);
        return ResultVO.ok(contentDetailVO);
    }

    @RequestMapping("/{userId}/others")
    public ResultVO others(@PathVariable Long userId){
        List<ContentSimpleVO> list = mapper.selectOthersByUserId(userId);

        return ResultVO.ok(list);
    }
    @RequestMapping("/hot")
    public ResultVO hot(){
        List<ContentSimpleVO> list = mapper.selectHot();
        return ResultVO.ok(list);
    }
    @RequestMapping("/{wd}/search")
    public ResultVO search(@PathVariable String wd){
        List<ContentIndexVO> list = mapper.selectByWd(wd);
        return ResultVO.ok(list);
    }
    @RequestMapping("/{type}/admin")
    public ResultVO admin(@PathVariable Integer type){
        List<ContentAdminVO> list = mapper.selectByTypeForAdmin(type);
        return ResultVO.ok(list);
    }
}
