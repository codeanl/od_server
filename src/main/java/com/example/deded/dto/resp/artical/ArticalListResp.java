package com.example.deded.dto.resp.artical;

import com.example.deded.pojo.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticalListResp {
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int mid;
    private String title;
    private String content;
    private String pic;
    private Member member;
}
