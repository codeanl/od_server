package com.example.deded.service;

import com.example.deded.dto.req.categgory.SaveOrUpdateCategoryReq;
import com.example.deded.dto.req.menu.SaveOrUpdateMenuReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.dto.resp.category.CategoryListResp;
import com.example.deded.pojo.Category;
import com.example.deded.pojo.Menu;

import java.util.List;

public interface CategoryService {
    //根据用户名查询角色
    Category findByCategoryName(String name);
    //
    void saveOrUpdateCategory(SaveOrUpdateCategoryReq req);
    //
    void deleteCategory( List<Integer> ids);

    List<CategoryListResp> categoryList();

}
