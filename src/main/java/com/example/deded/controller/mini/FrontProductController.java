package com.example.deded.controller.mini;

import com.example.deded.dto.req.product.ProductListReq;
import com.example.deded.pojo.Result;
import com.example.deded.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("front/product")
public class FrontProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/list")
    public Result productList(@ModelAttribute ProductListReq req) {
        return Result.success(productService.productList(req));
    }

    @GetMapping("/categoryAll")
    public Result categoryAll(Integer id) {
        return Result.success(productService.categoryAll(id));
    }
    @GetMapping("/info")
    public Result productInfo(Integer id) {
        return Result.success(productService.productInfo(id));
    }
}
