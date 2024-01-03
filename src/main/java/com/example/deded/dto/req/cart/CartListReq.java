package com.example.deded.dto.req.cart;

import lombok.Data;

@Data
public class CartListReq {
    private Integer pageNum;
    private Integer pageSize;
    private Integer mid;
    public CartListReq() {
        this.pageNum = 1; // 设置默认值为1
        this.pageSize = 10; // 设置默认值为10
    }
}
