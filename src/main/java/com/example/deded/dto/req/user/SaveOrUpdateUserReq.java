package com.example.deded.dto.req.user;

import lombok.Data;

@Data
public class SaveOrUpdateUserReq {
    private Integer id;
    private String phone;
    private String username;
    private String status;
}
