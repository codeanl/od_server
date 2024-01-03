package com.example.deded.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.pojo.Menu;
import com.example.deded.pojo.Role;
import com.example.deded.pojo.RoleMenu;
import com.example.deded.pojo.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    //
    @Transactional
    @Select("select * from menu where name=#{name}")
    Menu findByMenuName(String name);

    //添加
    @Insert("insert into menu(name,icon,path,pid,component,sort,status,created_at,updated_at)" +
            "values(#{name},#{icon},#{path},#{pid},#{component},#{sort},1,now(),now())")
    void menuAdd(Menu menu);
    //
    //更新
    @Update("UPDATE menu SET name = #{name}, icon = #{icon}, path = #{path}, pid = #{pid}, component = #{component}, sort = #{sort}, status = #{status}, updated_at = NOW() WHERE id = #{id}")
    void menuUpdate(Menu menu);
    //
    @Select("SELECT * FROM menu ORDER BY sort ASC")
    List<MenuListResp> menuList();
}
