package com.example.deded.dto.req.product;

import lombok.Data;

@Data
public class ProductListReq {
    private Integer pageNum;
    private Integer pageSize;
    private String name;
    private Integer categoryId;
    private Integer sortType;
    private float maxPrice;
    private float minPrice;
    public ProductListReq() {
        this.pageNum = 1; // 设置默认值为1
        this.pageSize = 10; // 设置默认值为10
    }
}
