package com.example.deded.dto.req.order;

import lombok.Data;

@Data
public class OrderListReq {
    private Integer pageNum;
    private Integer pageSize;
    private Integer mid;
    private String status;
    public OrderListReq() {
        this.pageNum = 1; // 设置默认值为1
        this.pageSize = 10; // 设置默认值为10
    }
}
