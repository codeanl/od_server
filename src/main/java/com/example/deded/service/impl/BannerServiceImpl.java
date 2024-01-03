package com.example.deded.service.impl;

import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.req.Banner.SaveOrUpdateBannerReq;
import com.example.deded.dto.req.categgory.SaveOrUpdateCategoryReq;
import com.example.deded.mapper.BannerMapper;
import com.example.deded.mapper.CategoryMapper;
import com.example.deded.pojo.Banner;
import com.example.deded.pojo.Category;
import com.example.deded.pojo.Role;
import com.example.deded.pojo.User;
import com.example.deded.service.BannerService;
import com.example.deded.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Banner findByBannerName(String name) {
        Banner u = bannerMapper.findByBannerName(name);
        return u;
    }

    @Override
    public void saveOrUpdateBanner(SaveOrUpdateBannerReq req) {
        Banner banner = new Banner();
        banner.setId(req.getId());
        banner.setName(req.getName());
        banner.setUrl(req.getUrl());
        banner.setStatus(req.getStatus());
        banner.setSort(req.getSort());
        if (req.getId()>0){
            bannerMapper.bannerUpdate(banner);
        }else{
            bannerMapper.bannerAdd(banner);
        }
    }

    @Override
    public void deleteBanner(List<Integer> ids) {
        bannerMapper.deleteBatchIds(ids);
    }

    //
    public PageInfo<Banner> bannerList(BannerListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<Banner> bannerList = bannerMapper.bannerList(req);
        return new PageInfo<>(bannerList);
    }

}
