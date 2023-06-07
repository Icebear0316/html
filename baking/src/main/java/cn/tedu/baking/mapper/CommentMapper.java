package cn.tedu.baking.mapper;


import cn.tedu.baking.pojo.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
    void insert(Comment comment);
}
