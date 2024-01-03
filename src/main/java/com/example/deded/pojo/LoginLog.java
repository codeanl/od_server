package com.example.deded.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private LocalDateTime createdAt;
    private Integer uid;
    private String ip;
}
