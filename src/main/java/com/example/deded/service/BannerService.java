package com.example.deded.service;

import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.req.Banner.SaveOrUpdateBannerReq;
import com.example.deded.dto.req.menu.SaveOrUpdateMenuReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.pojo.Banner;
import com.example.deded.pojo.Menu;
import com.example.deded.pojo.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BannerService {
    Banner findByBannerName(String name);
    //
    void saveOrUpdateBanner(SaveOrUpdateBannerReq req);

    //
    void deleteBanner( List<Integer> ids);
    //
    PageInfo<Banner> bannerList(BannerListReq req);
}
