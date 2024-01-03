package com.example.deded.controller.admin;

import com.example.deded.dto.req.categgory.SaveOrUpdateCategoryReq;
import com.example.deded.dto.req.menu.SaveOrUpdateMenuReq;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.dto.resp.category.CategoryListResp;
import com.example.deded.pojo.Category;
import com.example.deded.pojo.Menu;
import com.example.deded.pojo.Result;
import com.example.deded.service.CategoryService;
import com.example.deded.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //添加||更新分类
    @PostMapping("/category")
    public Result saveOrUpdateCategory(@RequestBody SaveOrUpdateCategoryReq req) {
//        Category u = categoryService.findByCategoryName(req.getName());
//        if (u == null) {
//            categoryService.saveOrUpdateCategory(req);
//            return Result.success();
//        }else{
//            return Result.error("该分类已被占用");
//        }
        categoryService.saveOrUpdateCategory(req);
        return Result.success();
    }
    @GetMapping("/list")
    public Result categoryList() {
        List<CategoryListResp> categoryListResp =categoryService.categoryList();
        List<CategoryListResp> ddd=buildCategoryTree(categoryListResp);
        return Result.success(ddd);
    }
    // 删除分类
    @PostMapping("/del")
    public Result deleteCategory(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        categoryService.deleteCategory(ids);
        return Result.success();
    }
    public List<CategoryListResp> buildCategoryTree(List<CategoryListResp> menuList) {
        // 使用一个 Map 来存储菜单项，以菜单项的 ID 作为键
        Map<Integer, CategoryListResp> menuMap = new HashMap<>();
        // 遍历菜单列表，将菜单项放入 Map 中
        for (CategoryListResp menu : menuList) {
            menuMap.put(menu.getId(), menu);
        }
        List<CategoryListResp> rootMenus = new ArrayList<>();
        // 遍历菜单列表，构建树状结构
        for (CategoryListResp menu : menuList) {
            int parentId = menu.getPid();
            if (parentId == 0) {
                // 如果父菜单 ID 为 0，则将该菜单项作为根菜单
                rootMenus.add(menu);
            } else {
                // 如果父菜单 ID 不为 0，则将菜单项添加到父菜单的 children 列表中
                CategoryListResp parentMenu = menuMap.get(parentId);
                if (parentMenu != null) {
                    parentMenu.addChild(menu);
                }
            }
        }
        return rootMenus;
    }

}
