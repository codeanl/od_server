package com.example.deded.controller;

import com.example.deded.dto.req.user.*;
import com.example.deded.dto.resp.User.UserInfoResp;
import com.example.deded.pojo.LoginLog;
import com.example.deded.pojo.Result;
import com.example.deded.pojo.User;
import com.example.deded.pojo.UserRole;
import com.example.deded.service.LoginLogService;
import com.example.deded.service.RedisService;
import com.example.deded.service.RoleService;
import com.example.deded.service.UserService;
import com.example.deded.utils.JwtUtil;
import com.example.deded.utils.Md5Util;
import com.example.deded.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    String dd="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOjEsInVzZXJuYW1lIjoiYWRtaW4ifSwiZXhwIjoxNzA0MjI1MjQyfQ.sH8SPmb60US_LmoiIxWqWg9zI3vVEh3FCkvEVtIb9uw";
    //用户登录
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginReq req) {
        //根据用户名查询用户
        User u = userService.findByUserName(req.getUsername());
        //判断该用户是否存在
        if (u == null) {
            return Result.error("用户不存在");
        }
        if (u.getStatus() == "0") {
            return Result.error("用户已被锁定");
        }
        //判断密码是否正确  loginUser对象中的password是密文
        if (Md5Util.getMD5String(req.getPassword()).equals(u.getPassword())) {
            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            String token = JwtUtil.genToken(claims);
            //
            loginLogService.addLoginLog(u.getId());
            //
            //把token存储到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }else{
            return Result.error("密码错误");
        }
    }
    //用户详情
    @GetMapping("/info") //todo 传假token
    public Result<UserInfoResp> userInfo() {
//        Map<String, Object> map = ThreadLocalUtil.get();
//        String username = (String) map.get("username");
        //
//        Map<String, Object> map = JwtUtil.parseToken(dd);
//        String username = (String) map.get("username");
        //
        User user = userService.findByUserName("admin");
        UserInfoResp userInfoResp=userService.userInfo(user.getId());
        return Result.success(userInfoResp);
    }
    //用户列表
    @GetMapping("/list")
    public Result getUsers(@ModelAttribute UserListReq request) {
        return Result.success(userService.userList(request.getPageNum(), request.getPageSize()));
    }
    // 更新个人信息
    @PostMapping("/updateInfo")
    public Result updateInfo( @RequestBody UpdateProfileReq updateProfileReq) {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        //
//        Map<String, Object> map = JwtUtil.parseToken(dd);
//        String username = (String) map.get("username");
        //
        User user = userService.findByUserName(username);
        updateProfileReq.setId(user.getId());
        userService.updateProfile(updateProfileReq);
        //
        return Result.success();
    }
    // 删除用户
    @PostMapping("/del")
    public Result deleteUser(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        userService.deleteUser(ids);
        return Result.success();
    }
    //添加
    @PostMapping("/register")
    public Result register(@RequestBody RegisterReq req) {
        //查询用户
        User u = userService.findByUserName(req.getUsername());
        if (u == null) {
            //
            User user = new User();
            user.setUsername(req.getUsername());
            user.setPassword( Md5Util.getMD5String(req.getPassword()));
            user.setNickname(req.getUsername());
            user.setPhone(req.getPhone());
            //
            userService.addUser(user);
            return Result.success();
        }else{
            return Result.error("用户名已被占用");
        }
    }

    // 更新个人信息
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody UpdateUserReq req) {
        User user = new User();
        user.setId(req.getId());
        user.setNickname(req.getNickname());
        user.setPhone(req.getPhone());
        user.setStatus(req.getStatus());
        user.setEmail(req.getEmail());
        user.setAvatar(req.getAvatar());
        userService.updateUser(user);
        //
        userService.DeleteByUserID(req.getId());
        //
        int[] ids = req.getRole_id();
        for (Integer id : ids) {
            UserRole userRole = new UserRole();
            userRole.setUser_id(req.getId());
            userRole.setRole_id(id);
            userService.UserRoleAdd(userRole);
        }
        //
        return Result.success();
    }

    // 更新个人密码 todo
    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody UpdatePwd req) {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(req.getOld_pwd()))){
            return Result.error("原密码填写不正确");
        }
//        //newPwd和rePwd是否一样
//        if (!req.getRe_pwd().equals(req.getNew_pwd())){
//            return Result.error("两次填写的新密码不一样");
//        }
        //密码更新
        userService.updatePwd(req.getPassword() ,loginUser.getId());
        return Result.success();
    }

    //
    @PostMapping("/user")
    public Result saveOrUpdateUser(@RequestBody SaveOrUpdateUserReq req){
        userService.saveOrUpdateUser(req);
        return Result.success();
    }

    @PostMapping("/updateUserRole")
    public Result updateUserRole(@RequestBody UpdateUserRoleReq req){
        userService.updateUserRole(req);
        return Result.success();
    }

}
