package cn.tedu.baking.controller;

import cn.tedu.baking.mapper.CommentMapper;
import cn.tedu.baking.mapper.ContentMapper;
import cn.tedu.baking.pojo.dto.CommentDTO;
import cn.tedu.baking.pojo.entity.Comment;
import cn.tedu.baking.pojo.vo.CommentVO;
import cn.tedu.baking.response.ResultVO;
import cn.tedu.baking.response.StatusCode;
import cn.tedu.baking.security.CustomUserDetails;
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
@RequestMapping("/v1/comments/")
public class CommentController {
    @Autowired
    CommentMapper mapper;
    @Autowired
    ContentMapper contentMapper;
    @RequestMapping("add-new")
    public ResultVO addNew(@RequestBody CommentDTO commentDTO,
                           @AuthenticationPrincipal CustomUserDetails userDetails){
        if (userDetails==null){
            return new ResultVO(StatusCode.NOT_LOGIN);
        }
        //评论数量+1
        contentMapper.updateCommentCount(commentDTO.getContentId());
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setUserId(userDetails.getId());
        comment.setCreateTime(new Date());
        mapper.insert(comment);
        return ResultVO.ok();
    }
    @RequestMapping("/{id}")
    public ResultVO list(@PathVariable Long id){
        List<CommentVO> list = mapper.selectByContentId(id);

        return ResultVO.ok(list);
    }
}
