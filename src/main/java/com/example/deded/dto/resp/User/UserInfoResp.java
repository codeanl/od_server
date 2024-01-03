package com.example.deded.dto.resp.User;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.pojo.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserInfoResp {
    private Integer id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String username;
    private String nickname;
    private String phone;
    private String avatar;
    private String email;
    private String status;
    private List<Role> role;
    private List<MenuListResp> menu;
}
