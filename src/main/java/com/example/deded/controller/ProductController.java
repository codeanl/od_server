package com.example.deded.controller;


import com.example.deded.dto.req.Banner.SaveOrUpdateBannerReq;
import com.example.deded.dto.req.product.ProductListReq;
import com.example.deded.dto.req.product.ProductSkuReq;
import com.example.deded.dto.req.product.SaveOrUpdateProductReq;
import com.example.deded.dto.req.role.RoleListReq;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.pojo.*;
import com.example.deded.service.BannerService;
import com.example.deded.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    //添加||更新商品
    @PostMapping("/product")
    public Result saveOrUpdateProduct(@RequestBody SaveOrUpdateProductReq req) {
//        Product u = productService.findByProductName(req.getName());
//        if (u == null) {
//            productService.saveOrUpdateProduct(req);
//            return Result.success();
//        }else{
//            return Result.error("该商品已被占用");
//        }
        productService.saveOrUpdateProduct(req);
        return Result.success();
    }

    // 删除
    @PostMapping("/del") //todo 删除关联
    public Result deleteProduct(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        productService.deleteProduct(ids);
        return Result.success();
    }

    //
    //列表
    @GetMapping("/list")
    public Result productList(@ModelAttribute ProductListReq req) {
        return Result.success(productService.productList(req));
    }

    //详情
    @GetMapping("/info")
    public Result productInfo(int id) {
        return Result.success(productService.productInfo(id));
    }

    //
    @GetMapping("/skuList")
    public Result skuList(Integer id) {
        return Result.success(productService.skuList(id));
    }

    //
    @PostMapping("updateSku") //todo 删除关联
    public Result updateSku(@RequestBody ProductSkuReq req) {
        productService.updateSku(req);
        return Result.success();
    }
}
