package com.example.deded.dto.req.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RoleAddReq {
    private String name;
    private String label;
}

