package com.example.deded.dto.req.product;

import lombok.Data;


@Data
public class ProductSkuReq {
    private Integer id;
    private String name;
    private String pic;
    private String desc;
    private double price;
    private int stock;
}
