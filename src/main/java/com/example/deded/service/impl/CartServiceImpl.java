package com.example.deded.service.impl;

import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.req.cart.CartListReq;
import com.example.deded.dto.req.cart.SaveOrUpdateCartReq;
import com.example.deded.dto.resp.cart.CartListResp;
import com.example.deded.mapper.BannerMapper;
import com.example.deded.mapper.CartMapper;
import com.example.deded.mapper.ProductMapper;
import com.example.deded.pojo.Banner;
import com.example.deded.pojo.Cart;
import com.example.deded.pojo.MemberLoginLog;
import com.example.deded.service.CartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    public PageInfo<CartListResp> cartList(CartListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<CartListResp> cartList = cartMapper.cartList(req);
        for (CartListResp i:cartList) {
            i.setSku(productMapper.skuInfoById(i.getSkuId()));
        }
        return new PageInfo<>(cartList);
    }
    @Override
    public void deleteCart(List<Integer> ids) {
        cartMapper.deleteBatchIds(ids);
    }

    @Override
    public void saveOrUpdateCart(SaveOrUpdateCartReq req){
        Cart cart=new Cart();
        cart.setId(req.getId());
        cart.setMid(req.getMid());
        cart.setSkuId(req.getSkuId());
        cart.setCount(req.getCount());
        cart.setSelected(req.getSelected());
        if(req.getId()>0){
            cartMapper.updateCart(cart);
        }else{
            cartMapper.addCart(cart);
        }
    }
}
