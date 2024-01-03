package com.example.deded.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.req.product.ProductListReq;
import com.example.deded.dto.resp.order.OrderInfoResp;
import com.example.deded.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Transactional
    @Select("select * from product where name=#{name}")
    Product findByProductName(String name);
    //
    //更新
    @Update("UPDATE product SET name = #{name}, pic = #{pic}, `desc` = #{desc}, price = #{price}, category_id = #{category_id} ,updated_at = NOW() WHERE id = #{id}")
    void productUpdate(Product product);
    //
    //添加
    @Transactional
    @Insert("insert into product(name,pic,`desc`,price,category_id,created_at,updated_at)" +
            "values(#{name},#{pic},#{desc},#{price},#{category_id},now(),now())")
    void productAdd(Product product);

    //添加
    @Insert("insert into product_img(product_id,url)" +
            "values(#{product_id},#{url})")
    void productImgAdd(ProductImg productImg);

    //添加
    @Insert("insert into product_detail_img(product_id,url)" +
            "values(#{product_id},#{url})")
    void productDetailImgAdd(ProductDetailImg productDetailImg);

    //
    @Delete("delete from product_img where product_id=#{product_id}")
    void productImgDelete(int product_id);

    //
    @Delete("delete from product_detail_img where product_id=#{product_id}")
    void productDetailImgDelete(int product_id);

    @Select({
            "<script>",
            "SELECT * FROM product",
            "<where>",
            "<if test='name != null'>AND name LIKE CONCAT('%', #{name}, '%')</if>",
            "<if test='categoryId != null'>AND category_id=#{categoryId}</if>",
            "</where>",
            "<choose>",
            "<when test='sortType == 0'>ORDER BY RAND()</when>",
            "<when test='sortType == 1'>ORDER BY created_at DESC</when>",
            "<when test='sortType == 2'>ORDER BY price DESC</when>",
            "<when test='sortType == 3'>ORDER BY price ASC</when>",
            "</choose>",
            "</script>"
    })
    List<Product> productList(ProductListReq req);

    //添加
    @Insert("insert into product_sku(name,pic,`desc`,price,stock,sale,tag,product_id,created_at,updated_at)" +
            "values(#{name},#{pic},#{desc},#{price},#{stock},#{sale},#{tag},#{productId},now(),now())")
    void productSkuAdd(ProductSku productSku);


    @Select("select * from product where id=#{id}")
    Product findByProductID(int  id);

    @Select("select * from product_sku where product_id=#{id}")
    List<ProductSku> skuList(int  id);



    @Update("UPDATE product_sku SET name = #{name}, pic = #{pic}, `desc` = #{desc}, price = #{price}, stock = #{stock} ,updated_at = NOW() WHERE id = #{id}")
    void updateSku(ProductSku productSku);

    @Select("select * from product_sku where id=#{id}")
    ProductSku skuInfoById(Integer  id);


    @Select("select * from product_img where product_id=#{id}")
    List<ProductImg> getProductImgByProId(Integer  id);

    @Select("select * from product_detail_img where product_id=#{id}")
    List<ProductDetailImg> getProductDetailImgByProId(Integer  id);
}
