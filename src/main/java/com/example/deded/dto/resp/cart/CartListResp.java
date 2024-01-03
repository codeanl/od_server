package com.example.deded.dto.resp.cart;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.deded.pojo.ProductSku;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartListResp {
    private Integer id;
    private int mid;
    private int skuId;
    private int count;
    private String selected;
    private ProductSku sku;
}
