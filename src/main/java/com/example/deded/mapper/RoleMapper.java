package com.example.deded.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.req.role.RoleListReq;
import com.example.deded.pojo.Role;
import com.example.deded.pojo.RoleMenu;
import com.example.deded.pojo.User;
import com.example.deded.pojo.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    //根据用户名查询
    @Transactional
    @Select("select * from role where name=#{name}")
    Role findByRoleName(String name);

    //添加
    @Insert("insert into role(name,label,status,created_at,updated_at)" +
            "values(#{name},#{label},1,now(),now())")
    void roleAdd(Role role);

    //更新
    @Update("update role set name=#{name},label=#{label},status=#{status},updated_at=now() where id=#{id}")
    void roleUpdate(Role role);

    //
    @Delete("delete from role_menu where role_id=#{roleId}")
    void DeleteRoleMenuByRoleID(int roleId);
    //
    @Insert("insert into role_menu(role_id,menu_id)" +
            "values(#{role_id},#{menu_id})")
    void roleMenuAdd(RoleMenu roleMenu);
    //
    @Select({
            "<script>",
            "SELECT * FROM role",
            "<where>",
            "<if test='name != null and name != &quot;&quot;'>AND name LIKE CONCAT('%', #{name}, '%')</if>",
            "<if test='label != null and label != &quot;&quot;'>AND label LIKE CONCAT('%', #{label}, '%')</if>",
            "<if test='status != null and status != &quot;&quot;'>AND status=#{status}</if>",
            "</where>",
            "</script>"
    })
//    @Select("SELECT * FROM role")
    List<Role> roleList(RoleListReq req);
}
