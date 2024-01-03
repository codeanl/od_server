package com.example.deded.service.impl;

import com.example.deded.dto.req.categgory.SaveOrUpdateCategoryReq;
import com.example.deded.dto.req.menu.SaveOrUpdateMenuReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.dto.resp.category.CategoryListResp;
import com.example.deded.mapper.CategoryMapper;
import com.example.deded.mapper.MenuMapper;
import com.example.deded.pojo.Category;
import com.example.deded.pojo.Menu;
import com.example.deded.service.CategoryService;
import com.example.deded.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findByCategoryName(String name) {
        Category u = categoryMapper.findByCategoryName(name);
        return u;
    }

    @Override
    public void saveOrUpdateCategory(SaveOrUpdateCategoryReq req) {
        Category category = new Category();
        category.setId(req.getId());
        category.setName(req.getName());
        category.setPic(req.getPic());
        category.setPid(req.getPid());
        category.setSort(req.getSort());
        category.setStatus(req.getStatus());
        if (req.getId()>0){
            categoryMapper.categoryUpdate(category);
        }else{
            categoryMapper.categoryAdd(category);
        }
    }

    @Override
    public void deleteCategory(List<Integer> ids) {
        categoryMapper.deleteBatchIds(ids);
    }

    public List<CategoryListResp> categoryList() {
        return categoryMapper.categoryList();
    }
    public void categoryAll(Integer id){

    }
}
