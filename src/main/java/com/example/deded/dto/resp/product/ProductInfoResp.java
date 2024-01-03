package com.example.deded.dto.resp.product;

import com.example.deded.pojo.ProductDetailImg;
import com.example.deded.pojo.ProductImg;
import com.example.deded.pojo.ProductSku;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductInfoResp {
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int category_id;
    private String name;
    private String pic;
    private String desc;
    private double price;
    private List<ProductImg> img;
    private List<ProductDetailImg> detailImg;
    private List<ProductSku> sku;
}
