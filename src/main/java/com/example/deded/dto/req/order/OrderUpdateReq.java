package com.example.deded.dto.req.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderUpdateReq {
    private Integer id;
    private String status;

}
