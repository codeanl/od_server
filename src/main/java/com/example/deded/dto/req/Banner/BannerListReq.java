package com.example.deded.dto.req.Banner;

import lombok.Data;

@Data
public class BannerListReq {
    private Integer pageNum;
    private Integer pageSize;
    private String status;
    private String name;
    public BannerListReq() {
        this.pageNum = 1; // 设置默认值为1
        this.pageSize = 10; // 设置默认值为10
    }
}
