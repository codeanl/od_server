package com.example.deded.dto.req.user;

import lombok.Data;

import java.util.List;

@Data
public class DeleteReq {
    private List<Integer> ids;
}
