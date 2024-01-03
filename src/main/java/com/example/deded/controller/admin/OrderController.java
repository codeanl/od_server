package com.example.deded.controller.admin;

import com.example.deded.dto.req.order.OrderListReq;
import com.example.deded.dto.req.order.OrderUpdateReq;
import com.example.deded.dto.req.product.ProductListReq;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.pojo.Order;
import com.example.deded.pojo.Result;
import com.example.deded.service.MenuService;
import com.example.deded.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //菜单列表
    @GetMapping("/list")
    public Result orderList(@ModelAttribute OrderListReq req) {
        return Result.success(orderService.orderList(req));
    }

    @GetMapping("/info")
    public Result orderInfo(Integer id) {
        return Result.success(orderService.orderInfo(id));
    }

    @PostMapping("/del") //todo 删除关联
    public Result deleteOrder(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        orderService.deleteOrder(ids);
        return Result.success();
    }

    @PostMapping("/update")
    public Result orderUpdate(@RequestBody OrderUpdateReq req) {
        orderService.orderUpdate(req);
        return Result.success();
    }
}
