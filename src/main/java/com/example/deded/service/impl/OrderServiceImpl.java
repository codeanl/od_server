package com.example.deded.service.impl;

import com.example.deded.dto.req.order.OrderAddReq;
import com.example.deded.dto.req.order.OrderListReq;
import com.example.deded.dto.req.order.OrderUpdateReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.dto.resp.order.OrderInfoResp;
import com.example.deded.mapper.MenuMapper;
import com.example.deded.mapper.OrderMapper;
import com.example.deded.mapper.ProductMapper;
import com.example.deded.pojo.Order;
import com.example.deded.pojo.OrderSku;
import com.example.deded.pojo.Product;
import com.example.deded.pojo.ProductSku;
import com.example.deded.service.MemberService;
import com.example.deded.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    public PageInfo<OrderInfoResp> orderList(OrderListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<OrderInfoResp> orderList = orderMapper.orderList(req);
        //
        for (OrderInfoResp i:orderList) {
            List<OrderSku> orderSku=orderMapper.orderSkuList(i.getId());
            List<OrderInfoResp.OrderSkuList> resultList = new ArrayList<>();
            for (OrderSku j:orderSku) {
                ProductSku o=productMapper.skuInfoById(j.getSkuId());
                OrderInfoResp.OrderSkuList oo=new OrderInfoResp.OrderSkuList();
                oo.setId(o.getId());
                oo.setDesc(o.getDesc());
                oo.setName(o.getName());
                oo.setPic(o.getPic());
                oo.setPrice(o.getPrice());
                oo.setStock(o.getStock());
                oo.setSale(o.getSale());
                oo.setTag(o.getTag());
                oo.setCount(j.getCount());
                resultList.add(oo);
            }
            i.setSku(resultList);
        }
        return new PageInfo<>(orderList);
    }


    @Override
    public void deleteOrder(List<Integer> ids) {
        orderMapper.deleteBatchIds(ids);
    }
    //
    public OrderInfoResp orderInfo(Integer id){
        OrderInfoResp u = orderMapper.findByOrderID(id);
        List<OrderSku> orderSku=orderMapper.orderSkuList(u.getId());
        List<OrderInfoResp.OrderSkuList> resultList = new ArrayList<>();
        for (OrderSku j:orderSku) {
            ProductSku o=productMapper.skuInfoById(j.getSkuId());
            OrderInfoResp.OrderSkuList oo=new OrderInfoResp.OrderSkuList();
            oo.setId(o.getId());
            oo.setDesc(o.getDesc());
            oo.setName(o.getName());
            oo.setPic(o.getPic());
            oo.setPrice(o.getPrice());
            oo.setStock(o.getStock());
            oo.setSale(o.getSale());
            oo.setTag(o.getTag());
            oo.setCount(j.getCount());
            resultList.add(oo);
        }
        u.setSku(resultList);
        return u;
    }

    public void orderAdd(OrderAddReq req){
        Order order=new Order();
        order.setMid(req.getMid());
        order.setOrderSn(req.getOrderSn());
        order.setTotalAmount(req.getTotalAmount());
        order.setFreightAmount(req.getFreightAmount());
        order.setPledgeAmount(req.getPledgeAmount());
        order.setBookedTime(req.getBookedTime());
        order.setName(req.getName());
        order.setPhone(req.getPhone());
        order.setAddress(req.getAddress());
        order.setOrderType(req.getOrderType());
        order.setPayType(req.getPayType());
        order.setStatus(req.getStatus());
        order.setNote(req.getNote());
        //
        orderMapper.orderAdd(order);
        Order ddddd=orderMapper.getOrderBySn(order.getOrderSn());
//        //
        for (OrderAddReq.Sku i: req.getSku() ) {
            OrderSku orderSku=new OrderSku();
            orderSku.setOrderId(ddddd.getId());
            orderSku.setSkuId(i.getSkuId());
            orderSku.setCount(i.getCount());
            orderMapper.orderSkuAdd(orderSku);
        }
    }

    public void orderUpdate(OrderUpdateReq req){
        Order order=new Order();
        order.setId(req.getId());
        order.setStatus(req.getStatus());
        orderMapper.orderUpdate(order);
    }

}
