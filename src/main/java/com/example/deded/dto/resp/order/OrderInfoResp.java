package com.example.deded.dto.resp.order;

import com.example.deded.pojo.OrderSku;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderInfoResp {
    private Integer id;
    private LocalDateTime createdTime;
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
    private LocalDateTime pickUpTime;
    private LocalDateTime returnTime;
    private String note;
    private List<OrderSkuList> sku;
    @Data
    public static class OrderSkuList{
        private Integer id;
        private int product_id;
        private String name;
        private String pic;
        private String desc;
        private double price;
        private int stock;
        private int sale;
        private String tag;
        private Integer count;
    }
}
