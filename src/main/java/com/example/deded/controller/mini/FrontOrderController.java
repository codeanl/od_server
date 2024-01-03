package com.example.deded.controller.mini;

import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.req.cart.CartListReq;
import com.example.deded.dto.req.order.OrderAddReq;
import com.example.deded.dto.req.order.OrderListReq;
import com.example.deded.pojo.Result;
import com.example.deded.service.BannerService;
import com.example.deded.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("front/order")
public class FrontOrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public Result orderAdd(@RequestBody OrderAddReq req) {
        orderService.orderAdd(req);
        return Result.success();
    }

    @GetMapping("/list")
    public Result orderList(@ModelAttribute OrderListReq req) {
        return Result.success(orderService.orderList(req));
    }

    @GetMapping("/info")
    public Result orderInfo(Integer id) {
        return Result.success(orderService.orderInfo(id));
    }
}




