package com.example.deded.dto.req.artical;

import lombok.Data;

@Data
public class ArticalListReq {
    private Integer pageNum;
    private Integer pageSize;
    private Integer mid;
    private String title;
    private Integer sortType;
    public ArticalListReq() {
        this.pageNum = 1; // 设置默认值为1
        this.pageSize = 10; // 设置默认值为10
    }
}
