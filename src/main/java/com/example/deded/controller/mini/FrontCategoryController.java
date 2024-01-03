package com.example.deded.controller.mini;

import com.example.deded.dto.resp.category.CategoryListResp;
import com.example.deded.pojo.Result;
import com.example.deded.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("front/category")
public class FrontCategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public Result categoryList() {
        List<CategoryListResp> categoryListResp =categoryService.categoryList();
        List<CategoryListResp> ddd=buildCategoryTree(categoryListResp);
        return Result.success(ddd);
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
