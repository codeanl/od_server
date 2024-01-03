package com.example.deded.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Cart {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int mid;
    private int skuId;
    private int count;
    private String selected;
}
