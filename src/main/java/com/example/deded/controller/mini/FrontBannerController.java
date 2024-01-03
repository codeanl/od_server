package com.example.deded.controller.mini;

import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.pojo.Result;
import com.example.deded.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("front/banner")
public class FrontBannerController {
    @Autowired
    private BannerService bannerService;
    //轮播图列表
    @GetMapping("/list")
    public Result frontBannerList(@ModelAttribute BannerListReq req) {
        req.setStatus("1");
        return Result.success(bannerService.bannerList(req));
    }
}
