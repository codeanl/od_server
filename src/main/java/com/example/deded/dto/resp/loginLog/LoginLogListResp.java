package com.example.deded.dto.resp.loginLog;

import com.example.deded.pojo.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginLogListResp {
    private Integer id;
    private LocalDateTime createdAt;
    private Integer uid;
    private String ip;
    private User user;
}
