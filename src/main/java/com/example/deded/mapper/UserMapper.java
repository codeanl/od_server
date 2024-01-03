package com.example.deded.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.req.user.UpdateProfileReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.pojo.User;
import com.example.deded.pojo.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;
import com.example.deded.pojo.Role;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    //根据用户名查询用户
    @Transactional
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    //添加
    @Insert("insert into user(username,password,nickname,phone,status,avatar,created_at,updated_at)" +
            "values(#{username},#{password},#{username},#{phone},1,'avatar',now(),now())")
    void add(User user);

    @Update("update user set password=#{password} where id=#{id}")
    void updatePwd(User user);


    List<User> list();


    @Select("SELECT * FROM user")
    List<User> listUsers();

    //添加
    @Insert("insert into user_role(user_id,role_id)" +
            "values(#{user_id},#{role_id})")
    void UserRoleAdd(UserRole user_role);

    @Delete("delete from user_role where user_id=#{user_id}")
    void DeleteUserRoleByUserID(int user_id);

    @Select("select * from user where id=#{id}")
    User findByUserID(Integer id);

    //
    @Select("SELECT r.* FROM role r INNER JOIN user_role ur ON r.id = ur.role_id WHERE ur.user_id = #{id}")
    List<Role> getUserRole(Integer id);
    //


    @Select("select role_id from user_role where user_id=#{id}")
    List<Integer> getRoleIdsByUserId(Integer id);


    @Select("select menu_id from role_menu where role_id=#{roleId}")
    List<Integer> getMenuIdsByRoleIds(Integer roleId);

    //
    @Select("select * from menu where id=#{id}")
    MenuListResp getMenuByMenuId(Integer id);

    //
    @Update("update user set nickname=#{nickname},email=#{email},phone=#{phone} where id=#{id}")
    void updateProfile(UpdateProfileReq updateProfileReq);

//    updateUser
//     addUser
    @Insert("insert into user(username,password,nickname,phone,status,avatar,created_at,updated_at)" +
        "values(#{username},#{password},#{username},#{phone},1,#{avatar},now(),now())")
    void addUser(User user);

    //
    @Update("update user set username=#{username},phone=#{phone},status=#{status},updated_at=now() where id=#{id}")
    void updateUser(User user);
}
