package com.example.deded.dto.resp.category;

import com.example.deded.pojo.Product;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FrontCategoryNextAndProResp {
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedA;
    private String name;
    private String pic;
    private int pid;
    private int sort;
    private String status;
    private List<Product> product;
}
