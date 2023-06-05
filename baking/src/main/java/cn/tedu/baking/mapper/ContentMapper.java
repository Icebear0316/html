package cn.tedu.baking.mapper;


import cn.tedu.baking.pojo.entity.Content;
import cn.tedu.baking.pojo.vo.ContentEditVO;
import cn.tedu.baking.pojo.vo.ContentManagementVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper {
    int insert(Content content);

    List<ContentManagementVO> selectByType(Integer type,Long id);

    ContentEditVO selectByIdForEdit(Long id);
}
