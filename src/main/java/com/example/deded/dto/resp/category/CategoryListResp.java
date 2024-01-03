package com.example.deded.dto.resp.category;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.deded.dto.resp.Menu.MenuListResp;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryListResp {
    private Integer id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String name;
    private String pic;
    private int pid;
    private int sort;
    private String status;
    private List<CategoryListResp> children;
    public void addChild(CategoryListResp child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }
}
