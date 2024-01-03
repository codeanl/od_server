package com.example.deded.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.req.artical.ArticalListReq;
import com.example.deded.dto.req.artical.SaveOrUpdateArticalReq;
import com.example.deded.dto.req.product.ProductListReq;
import com.example.deded.dto.resp.artical.ArticalListResp;
import com.example.deded.pojo.Artical;
import com.example.deded.pojo.Banner;
import com.example.deded.pojo.Category;
import com.example.deded.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticalMapper  extends BaseMapper<Artical> {

//添加
    @Insert("insert into artical(mid,title,content,pic,created_at,updated_at)" +
        "values(#{mid},#{title},#{content},#{pic},now(),now())")
    void articalAdd(Artical artical);

    //更新
    @Update("UPDATE artical SET title = #{title}, content = #{content}, pic = #{pic}, updated_at = NOW() WHERE id = #{id}")
    void articalUpdate(Artical artical);

    @Select({
            "<script>",
            "SELECT * FROM artical",
            "<where>",
            "<if test='title != null'>AND title LIKE CONCAT('%', #{title}, '%')</if>",
            "<if test='mid != null'>AND mid=#{mid}</if>",
            "</where>",
            "<choose>",
            "<when test='sortType == 0'>ORDER BY RAND()</when>",
            "<when test='sortType == 1'>ORDER BY created_at DESC</when>",
            "</choose>",
            "</script>"
    })
    List<ArticalListResp> articalList(ArticalListReq req);

    @Select("select * from artical where id=#{id}")
    Artical articalInfo(Integer id);

}
