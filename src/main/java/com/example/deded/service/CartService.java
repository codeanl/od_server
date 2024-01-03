package com.example.deded.service;

import com.example.deded.dto.req.cart.CartListReq;
import com.example.deded.dto.req.cart.SaveOrUpdateCartReq;
import com.example.deded.dto.resp.cart.CartListResp;
import com.example.deded.dto.resp.category.CategoryListResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CartService {

    PageInfo<CartListResp> cartList(CartListReq req);
    void deleteCart( List<Integer> ids);
    void saveOrUpdateCart(SaveOrUpdateCartReq req);

}
