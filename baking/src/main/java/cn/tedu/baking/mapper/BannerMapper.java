package cn.tedu.baking.mapper;

import cn.tedu.baking.pojo.vo.BannerAdminVO;
import cn.tedu.baking.pojo.vo.BannerVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerMapper {

    List<BannerVO> select();

    List<BannerAdminVO> selectForAdmin();

    void deleteById(Long id);
}
