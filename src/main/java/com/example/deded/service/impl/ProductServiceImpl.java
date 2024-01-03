package com.example.deded.service.impl;

import com.example.deded.dto.req.product.ProductListReq;
import com.example.deded.dto.req.product.ProductSkuReq;
import com.example.deded.dto.req.product.SaveOrUpdateProductReq;
import com.example.deded.dto.resp.category.FrontCategoryNextAndProResp;
import com.example.deded.dto.resp.product.ProductInfoResp;
import com.example.deded.mapper.CategoryMapper;
import com.example.deded.mapper.ProductMapper;
import com.example.deded.pojo.*;
import com.example.deded.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    //
    @Override
    public Product findByProductName(String name) {
        Product u = productMapper.findByProductName(name);
        return u;
    }
    //
    @Override
    public void saveOrUpdateProduct(SaveOrUpdateProductReq req) {
        Product product = new Product();
        product.setId(req.getId());
        product.setName(req.getName());
        product.setCategory_id(req.getCategory_id());
        product.setPic(req.getPic());
        product.setPrice(req.getPrice());
        product.setDesc(req.getDesc());
        //
        int pid;
        if (req.getId()>0){
            pid=req.getId();
            productMapper.productImgDelete(req.getId());
            productMapper.productDetailImgDelete(req.getId());
            productMapper.productUpdate(product);
            //
            //
        }else{
           productMapper.productAdd(product);
            Product u = productMapper.findByProductName(req.getName());
            pid=u.getId();
            // 存储结果组合的列表
            List<Map<String, String>> combinations = new ArrayList<>();
            for (SaveOrUpdateProductReq.Size size : req.getSize()) {
                String name = size.getName();
                String value = size.getValue();
                // 根据逗号分隔选项值
                String[] values = value.split(",");
                // 如果结果列表为空，则直接添加每个选项值作为初始组合
                if (combinations.isEmpty()) {
                    for (String v : values) {
                        Map<String, String> combination = new LinkedHashMap<>();
                        combination.put("\"" + name + "\"", "\"" + v + "\"");
                        combinations.add(combination);
                    }
                } else {
                    // 如果结果列表不为空，则在现有组合的基础上进行扩展
                    List<Map<String, String>> newCombinations = new ArrayList<>();
                    for (Map<String, String> combination : combinations) {
                        for (String v : values) {
                            // 创建新的组合对象
                            Map<String, String> newCombination = new LinkedHashMap<>(combination);
                            newCombination.put("\"" + name + "\"", "\"" + v + "\"");
                            newCombinations.add(newCombination);
                        }
                    }
                    combinations = newCombinations;
                }
            }
            for (Map<String, String> combination : combinations) {
                // 遍历组合中的属性和值，并将等号替换为冒号
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                for (Map.Entry<String, String> entry : combination.entrySet()) {
                    sb.append(entry.getKey()).append(":").append(entry.getValue()).append(", ");
                }
                // 去除末尾的逗号和空格
                String result = sb.substring(0, sb.length() - 2);
                result += "}";
                System.out.println(result);
                ProductSku productSku =new ProductSku();
                productSku.setName(req.getName());
                productSku.setPic(req.getPic());
                productSku.setDesc(req.getDesc());
                productSku.setPrice(req.getPrice());
                productSku.setStock(100);
                productSku.setSale(0);
                productSku.setProductId(pid);
                productSku.setTag(result);
                productMapper.productSkuAdd(productSku);
            }
            //
        }
        //
        for (String i :  req.getImg()) {
            ProductImg productImg = new ProductImg();
            productImg.setProduct_id(pid);
            productImg.setUrl(i);
            productMapper.productImgAdd(productImg);
        }
        //
        for (String i :  req.getDetail_img()) {
            ProductDetailImg productDetailImg = new ProductDetailImg();
            productDetailImg.setProduct_id(pid);
            productDetailImg.setUrl(i);
            productMapper.productDetailImgAdd(productDetailImg);
        }
    }
    //
    @Override
    public void deleteProduct(List<Integer> ids) {
        productMapper.deleteBatchIds(ids);
    }

    //
    public PageInfo<Product> productList(ProductListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<Product> productList = productMapper.productList(req);
        return new PageInfo<>(productList);
    }

    //

    public ProductInfoResp productInfo(Integer id){
        Product u = productMapper.findByProductID(id);
        ProductInfoResp productInfoResp =new ProductInfoResp();
        productInfoResp.setId(u.getId());
        productInfoResp.setCreatedAt(u.getCreatedAt());
        productInfoResp.setUpdatedAt(u.getUpdatedAt());
        productInfoResp.setCategory_id(u.getCategory_id());
        productInfoResp.setName(u.getName());
        productInfoResp.setPic(u.getPic());
        productInfoResp.setPic(u.getPic());
        productInfoResp.setDesc(u.getDesc());
        productInfoResp.setPrice(u.getPrice());
        //
        productInfoResp.setImg(productMapper.getProductImgByProId(u.getId()));
        productInfoResp.setDetailImg(productMapper.getProductDetailImgByProId(u.getId()));
        //
        productInfoResp.setSku( productMapper.skuList(u.getId()));
        return productInfoResp;
    }


    public List<ProductSku> skuList(Integer id){
        List<ProductSku> list=productMapper.skuList(id);
        return list;
    }

    public void updateSku( ProductSkuReq req){
        ProductSku sku = new ProductSku();
        sku.setId(req.getId());
        sku.setName(req.getName());
        sku.setDesc(req.getDesc());
        sku.setPic(req.getPic());
        sku.setStock(req.getStock());
        sku.setPrice(req.getPrice());
        productMapper.updateSku(sku);
    }

    public List<FrontCategoryNextAndProResp> categoryAll(Integer id){
        List<FrontCategoryNextAndProResp> ddd=categoryMapper.getCategoryByPid(id);
        for (FrontCategoryNextAndProResp i:ddd) {
            ProductListReq req=new ProductListReq();
            req.setCategoryId(i.getId());
            req.setPageNum(1);
            req.setPageSize(6);
            List<Product>pro= productMapper.productList(req);
            i.setProduct(pro);
        }
        return ddd;
    }
}
