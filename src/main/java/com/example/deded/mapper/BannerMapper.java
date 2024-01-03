package com.example.deded.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.pojo.Banner;
import com.example.deded.pojo.Category;
import com.example.deded.pojo.Menu;
import com.example.deded.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface BannerMapper extends BaseMapper<Banner> {
    @Transactional
    @Select("select * from banner where name=#{name}")
    Banner findByBannerName(String name);

    //添加
    @Insert("insert into banner(name,url,status,sort,created_at,updated_at)" +
            "values(#{name},#{url},1,#{sort},now(),now())")
    void bannerAdd(Banner banner);

    //更新
    @Update("UPDATE banner SET name = #{name}, url = #{url}, status = #{status}, sort = #{sort}, updated_at = NOW() WHERE id = #{id}")
    void bannerUpdate(Banner banner);

    //
    @Select({"<script>",
            "SELECT * FROM banner ",
            "<where>",
            "<if test='name != null and name != &quot;&quot;'>AND name LIKE CONCAT('%', #{name}, '%')</if>",
            "<if test='status != null and status != &quot;&quot;'>AND status=#{status}</if>",
            "</where>",
            "ORDER BY sort ASC",
            "</script>"})
    List<Banner> bannerList(BannerListReq req);

}
