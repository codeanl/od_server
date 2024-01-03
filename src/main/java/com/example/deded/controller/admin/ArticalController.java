package com.example.deded.controller.admin;

import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.req.Banner.SaveOrUpdateBannerReq;
import com.example.deded.dto.req.artical.ArticalListReq;
import com.example.deded.dto.req.artical.SaveOrUpdateArticalReq;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.pojo.Result;
import com.example.deded.service.ArticalService;
import com.example.deded.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/artical")
public class ArticalController {
    @Autowired
    private ArticalService articalService;
    //添加||更新轮播图
    @PostMapping("/artical")
    public Result saveOrUpdateBanner(@RequestBody SaveOrUpdateArticalReq req) {
        articalService.saveOrUpdateArtical(req);
        return Result.success();
    }

    //轮播图列表
    @GetMapping("/list")
    public Result articalList(@ModelAttribute ArticalListReq req) {
        return Result.success(articalService.articalList(req));
    }

    @PostMapping("/del")
    public Result deleteArtical(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        articalService.deleteArtical(ids);
        return Result.success();
    }
}
