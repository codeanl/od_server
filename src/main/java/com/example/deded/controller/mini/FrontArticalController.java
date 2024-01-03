package com.example.deded.controller.mini;

import com.example.deded.dto.req.artical.ArticalListReq;
import com.example.deded.dto.req.artical.SaveOrUpdateArticalReq;
import com.example.deded.dto.req.cart.SaveOrUpdateCartReq;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.pojo.Result;
import com.example.deded.service.ArticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("front/artical")
public class FrontArticalController {
    @Autowired
    private ArticalService articalService;
    //轮播图列表
    @GetMapping("/list")
    public Result articalList(@ModelAttribute ArticalListReq req) {
        return Result.success(articalService.articalList(req));
    }
    @GetMapping("/info")
    public Result articalInfo(Integer id) {
        return Result.success(articalService.articalInfo(id));
    }

    @PostMapping("/artical")
    public Result saveOrUpdateArtical(@RequestBody SaveOrUpdateArticalReq req) {
        articalService.saveOrUpdateArtical(req);
        return Result.success();
    }

    @PostMapping("/del")
    public Result deleteArtical(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        articalService.deleteArtical(ids);
        return Result.success();
    }
}

