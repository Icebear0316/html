package cn.tedu.baking.controller;

import cn.tedu.baking.mapper.CommentMapper;
import cn.tedu.baking.pojo.dto.CommentDTO;
import cn.tedu.baking.pojo.entity.Comment;
import cn.tedu.baking.response.ResultVO;
import cn.tedu.baking.response.StatusCode;
import cn.tedu.baking.security.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/v1/comments/")
public class CommentController {
    @Autowired
    CommentMapper mapper;

    @RequestMapping("add-new")
    public ResultVO addNew(@RequestBody CommentDTO commentDTO,
                           @AuthenticationPrincipal CustomUserDetails userDetails){
        if (userDetails==null){
            return new ResultVO(StatusCode.NOT_LOGIN);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setUserId(userDetails.getId());
        comment.setCreateTime(new Date());
        mapper.insert(comment);
        return ResultVO.ok();
    }
}
