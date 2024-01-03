package com.example.deded.controller.mini;

import com.example.deded.dto.req.cart.CartListReq;
import com.example.deded.dto.req.cart.SaveOrUpdateCartReq;
import com.example.deded.dto.req.product.ProductListReq;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.dto.resp.category.CategoryListResp;
import com.example.deded.pojo.Result;
import com.example.deded.service.BannerService;
import com.example.deded.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("front/cart")
public class FrontCartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/list")
    public Result cartList(@ModelAttribute CartListReq req) {
        return Result.success(cartService.cartList(req));
    }

    // 删除角色
    @PostMapping("/del")
    public Result deleteCart(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        cartService.deleteCart(ids);
        return Result.success();
    }

    @PostMapping("/cart")
    public Result saveOrUpdateCart(@RequestBody SaveOrUpdateCartReq req) {
        cartService.saveOrUpdateCart(req);
        return Result.success();
    }


}
