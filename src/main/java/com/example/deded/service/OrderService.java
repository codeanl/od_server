package com.example.deded.service;

import com.example.deded.dto.req.order.OrderAddReq;
import com.example.deded.dto.req.order.OrderListReq;
import com.example.deded.dto.req.order.OrderUpdateReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.dto.resp.order.OrderInfoResp;
import com.example.deded.pojo.Order;
import com.example.deded.pojo.Product;
import com.example.deded.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {
    PageInfo<OrderInfoResp> orderList(OrderListReq req) ;

    void deleteOrder( List<Integer> ids);

    OrderInfoResp orderInfo(Integer id);

    void orderAdd(OrderAddReq req);
    void orderUpdate(OrderUpdateReq req);


}
