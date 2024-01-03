package com.example.deded.service;

import com.example.deded.dto.resp.loginLog.LoginLogListResp;
import com.example.deded.pojo.LoginLog;
import com.example.deded.pojo.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface LoginLogService {
    PageInfo<LoginLogListResp> loginLogList(int pageNum, int pageSize);
    //
    void deleteLoginLog( List<Integer> ids);

    void addLoginLog( Integer id);
}
