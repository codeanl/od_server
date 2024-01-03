package com.example.deded.dto.req.cart;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaveOrUpdateCartReq {
    private Integer id;
    private int mid;
    private int skuId;
    private int count;
    private String selected;
}
