package com.example.deded.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductSku {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int productId;
    private String name;
    private String pic;
    private String desc;
    private double price;
    private int stock;
    private int sale;
    private String tag;
}
