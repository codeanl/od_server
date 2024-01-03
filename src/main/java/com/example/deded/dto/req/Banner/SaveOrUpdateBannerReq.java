package com.example.deded.dto.req.Banner;

import lombok.Data;

@Data
public class SaveOrUpdateBannerReq {
    private Integer id;
    private String name;
    private String url;
    private String status;
    private int sort;
}
