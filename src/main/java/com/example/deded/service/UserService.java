package com.example.deded.service;

import com.example.deded.dto.req.user.SaveOrUpdateUserReq;
import com.example.deded.dto.req.user.UpdateProfileReq;
import com.example.deded.dto.req.user.UpdateUserRoleReq;
import com.example.deded.dto.resp.User.UserInfoResp;
import com.example.deded.pojo.PageBean;
import com.example.deded.pojo.User;
import com.example.deded.pojo.UserRole;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateProfile(UpdateProfileReq updateProfileReq);
    void updateUser(User user);
    User getUserById(int id);
    void deleteUser( List<Integer> ids);
    //根据用户名查询用户
    User findByUserName(String username);
    //更新密码
    void updatePwd(String password,Integer id);


    PageInfo<User> userList(int pageNum, int pageSize);

    //根据用户名查询用户
    void UserRoleAdd(UserRole user_role);
    void DeleteByUserID(int user_id);

    //
    UserInfoResp userInfo(Integer id);


    void saveOrUpdateUser(SaveOrUpdateUserReq req);

    //
    void updateUserRole(UpdateUserRoleReq req);

}
