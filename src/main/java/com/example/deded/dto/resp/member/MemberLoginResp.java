package com.example.deded.dto.resp.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MemberLoginResp {
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String phone;
    private String nickname;
    private String avatar;
    private String email;
    private String intro;
    private String status;
    private String token;
}
