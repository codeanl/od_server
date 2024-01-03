package com.example.deded.dto.req.menu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@Data
public class SaveOrUpdateMenuReq {
    private Integer id;
    private String name;
    private String icon;
    private String path;
    private int pid;
    private String component;
    private int sort;
    private String status;
}
