package com.example.deded.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.req.cart.CartListReq;
import com.example.deded.dto.req.product.ProductListReq;
import com.example.deded.dto.resp.cart.CartListResp;
import com.example.deded.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    @Select({
            "<script>",
            "SELECT * FROM cart",
            "<where>",
            "<if test='mid != null'>AND mid=#{mid}</if>",
            "</where>",
            "</script>"
    })
    List<CartListResp> cartList(CartListReq req);

    @Insert("insert into cart(mid,sku_id,count,selected,created_at,updated_at)" +
            "values(#{mid},#{skuId},#{count},0,now(),now())")
    void addCart(Cart cart);

    @Update("UPDATE cart SET count = #{count}, selected = #{selected},updated_at = NOW() WHERE id = #{id}")
    void updateCart(Cart cart);
}
