package com.example.deded.dto.resp.Menu;

import com.example.deded.pojo.Menu;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MenuListResp {
    private Integer id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String name;
    private String icon;
    private String path;
    private int pid;
    private String component;
    private int sort;
    private String status;
    private List<MenuListResp> children;
    public void addChild(MenuListResp child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }
}
