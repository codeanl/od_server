package com.example.deded.controller.admin;

import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.req.loginLog.LoginLogListReq;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.pojo.Result;
import com.example.deded.service.CategoryService;
import com.example.deded.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/loginLog")
public class LoginLogController {
    @Autowired
    private LoginLogService loginLogService;
    // 删除
    @PostMapping("/del")
    public Result deleteLoginLog(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        loginLogService.deleteLoginLog(ids);
        return Result.success();
    }

    //列表
    @GetMapping("/list")
    public Result loginLogList(@ModelAttribute LoginLogListReq req) {
        return Result.success(loginLogService.loginLogList(req.getPageNum(), req.getPageSize()));
    }
}
