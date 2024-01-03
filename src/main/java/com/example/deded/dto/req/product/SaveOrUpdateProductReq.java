package com.example.deded.dto.req.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaveOrUpdateProductReq {
    private Integer id;//todo
    private int category_id;
    private String name;
    private String pic;
    private String desc;
    private double price;
    private List<String> img;
    private List<String> detail_img;
    private List<Size> size;
    @Data
    public static class Size {
        private String name;
        private String value;
    }
}
