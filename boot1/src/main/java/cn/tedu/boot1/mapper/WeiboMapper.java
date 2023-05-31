package cn.tedu.boot1.mapper;

import cn.tedu.boot1.pojo.entity.Weibo;
import cn.tedu.boot1.pojo.vo.WeiboIndexVO;

import java.util.List;

public interface WeiboMapper {
    int insert(Weibo weibo);

    List<WeiboIndexVO> select();


    void deleteById(Integer id);
}
