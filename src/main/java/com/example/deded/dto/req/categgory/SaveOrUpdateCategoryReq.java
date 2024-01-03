package com.example.deded.dto.req.categgory;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaveOrUpdateCategoryReq {
    private Integer id; //todo id可选
    private String name;
    private String pic;
    private int pid;
    private int sort;
    private String status;
}
