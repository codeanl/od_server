package com.example.deded.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    @TableId(type = IdType.AUTO)
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
}
