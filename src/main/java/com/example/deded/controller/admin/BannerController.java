package com.example.deded.controller.admin;

import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.req.Banner.SaveOrUpdateBannerReq;
import com.example.deded.dto.req.categgory.SaveOrUpdateCategoryReq;
import com.example.deded.dto.req.role.RoleListReq;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.pojo.Banner;
import com.example.deded.pojo.Category;
import com.example.deded.pojo.Result;
import com.example.deded.service.BannerService;
import com.example.deded.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    //添加||更新轮播图
    @PostMapping("/banner")
    public Result saveOrUpdateBanner(@RequestBody SaveOrUpdateBannerReq req) {
        bannerService.saveOrUpdateBanner(req);
        return Result.success();
    }
    // 删除轮播图
    @PostMapping("/del")
    public Result deleteBanner(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        bannerService.deleteBanner(ids);
        return Result.success();
    }
    //轮播图列表
    @GetMapping("/list")
    public Result bannerList(@ModelAttribute BannerListReq req) {
        return Result.success(bannerService.bannerList( req));
    }
}
