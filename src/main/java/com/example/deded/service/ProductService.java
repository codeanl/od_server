package com.example.deded.service;

import com.example.deded.dto.req.menu.SaveOrUpdateMenuReq;
import com.example.deded.dto.req.product.ProductListReq;
import com.example.deded.dto.req.product.ProductSkuReq;
import com.example.deded.dto.req.product.SaveOrUpdateProductReq;
import com.example.deded.dto.resp.category.FrontCategoryNextAndProResp;
import com.example.deded.dto.resp.product.ProductInfoResp;
import com.example.deded.pojo.Category;
import com.example.deded.pojo.Product;
import com.example.deded.pojo.ProductSku;
import com.example.deded.pojo.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {
    //
    Product findByProductName(String name);

    //
    void saveOrUpdateProduct(SaveOrUpdateProductReq req);
    //
    void deleteProduct( List<Integer> ids);

    PageInfo<Product> productList(ProductListReq req);

    //
    ProductInfoResp productInfo(Integer id);


    List<ProductSku> skuList(Integer id);

    void updateSku( ProductSkuReq req);

    List<FrontCategoryNextAndProResp> categoryAll(Integer id);

}
