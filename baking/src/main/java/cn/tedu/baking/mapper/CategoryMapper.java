package cn.tedu.baking.mapper;


import cn.tedu.baking.pojo.vo.CategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    List<CategoryVO> selectByType(Integer type);
}
