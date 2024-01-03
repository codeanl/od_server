package com.example.deded.pojo;

import lombok.Data;

@Data
public class OrderSku {
    private Integer orderId;
    private Integer skuId;
    private Integer count;
}
