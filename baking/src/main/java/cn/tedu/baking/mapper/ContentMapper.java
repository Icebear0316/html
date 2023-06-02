package cn.tedu.baking.mapper;


import cn.tedu.baking.pojo.entity.Content;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentMapper {
    int insert(Content content);

}
