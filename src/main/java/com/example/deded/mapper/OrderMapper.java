package com.example.deded.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.req.order.OrderListReq;
import com.example.deded.dto.resp.order.OrderInfoResp;
import com.example.deded.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select({
            "<script>",
            "SELECT * FROM `order`",
            "<where>",
            "<if test='mid != null'>AND mid = #{mid}</if>",
            "<if test='status != null and status != 0'>AND status = #{status}</if>",
            "</where>",
            "ORDER BY created_time DESC",
            "</script>"
    })
    List<OrderInfoResp> orderList(OrderListReq req);

    @Select("select * from `order` where id=#{id}")
    OrderInfoResp findByOrderID(Integer  id);

    @Select("select * from order_sku where order_id=#{id}")
    List<OrderSku> orderSkuList(Integer  id);

    //添加
    @Insert("insert into `order`(mid,order_sn,total_amount,freight_amount,pledge_amount,booked_time,name,phone,address,order_type,pay_type,status,note,created_time,payment_time)" +
            "values(#{mid},#{orderSn},#{totalAmount},#{freightAmount},#{pledgeAmount},#{bookedTime},#{name},#{phone},#{address},#{orderType},#{payType},#{status},#{note},now(),now())")
    void orderAdd(Order order);


    @Insert("insert into order_sku(order_id,sku_id,count)" +
            "values(#{orderId},#{skuId},#{count})")
    void orderSkuAdd(OrderSku orderSku);

    @Select("select * from `order` where order_sn=#{sn}")
    Order getOrderBySn(String  sn);

//    @Update("UPDATE `order` " +
//            "SET " +
//            "<if test=\"status == 3\">" +
//            "  status = #{status}," +
//            "</if>" +
//            "<if test=\"status == 4\">" +
//            "  status = #{status}," +
//            "  pick_up_time = NOW()" +
//            "</if>" +
//            "<if test=\"status == 5\">" +
//            "  status = #{status}," +
//            "  return_time = NOW()" +
//            "</if>" +
//            "WHERE id = #{id}")
    @Update("UPDATE `order` SET status = #{status} WHERE id = #{id}")
    void orderUpdate(Order order);
}
