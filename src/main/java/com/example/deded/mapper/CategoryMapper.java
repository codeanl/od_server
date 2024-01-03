package com.example.deded.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.dto.resp.category.CategoryListResp;
import com.example.deded.dto.resp.category.FrontCategoryNextAndProResp;
import com.example.deded.pojo.Category;
import com.example.deded.pojo.Member;
import com.example.deded.pojo.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper

public interface CategoryMapper extends BaseMapper<Category> {
    //更新
    @Update("UPDATE category SET name = #{name}, pic = #{pic}, pid = #{pid}, sort = #{sort}, status = #{status} ,updated_at = NOW() WHERE id = #{id}")
    void categoryUpdate(Category category);
    //
    @Transactional
    @Select("select * from category where name=#{name}")
    Category findByCategoryName(String name);

    //添加
    @Insert("insert into category(name,pic,pid,sort,status,created_at,updated_at)" +
            "values(#{name},#{pic},#{pid},#{sort},1,now(),now())")
    void categoryAdd(Category category);

    @Select("SELECT * FROM category ORDER BY sort ASC")
    List<CategoryListResp> categoryList();


    @Select("select * from category where pid=#{id}")
    List<FrontCategoryNextAndProResp> getCategoryByPid(Integer id);

}
