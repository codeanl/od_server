package com.example.deded.dto.req.order;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderAddReq {
    private int mid;
    private String orderSn;
    private double totalAmount;
    private double freightAmount;
    private double pledgeAmount;
    private LocalDateTime bookedTime;
    private String name;
    private String phone;
    private String address;
    private String orderType;
    private String payType;
    private String status;
    private LocalDateTime paymentTime;
    private String note;
    private List<Sku> sku;
    @Data
    public static class Sku {
        private int orderId;
        private int skuId;
        private int count;
    }
}
