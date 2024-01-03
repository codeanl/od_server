package com.example.deded.dto.req.artical;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaveOrUpdateArticalReq {
    private Integer id;
    private int mid;
    private String title;
    private String content;
    private String pic;
}
