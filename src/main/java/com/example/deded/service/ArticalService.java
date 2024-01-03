package com.example.deded.service;

import com.example.deded.dto.req.artical.ArticalListReq;
import com.example.deded.dto.req.artical.SaveOrUpdateArticalReq;
import com.example.deded.dto.resp.artical.ArticalListResp;
import com.example.deded.pojo.Artical;
import com.example.deded.pojo.Banner;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticalService {
    void saveOrUpdateArtical(SaveOrUpdateArticalReq req);

    PageInfo<ArticalListResp> articalList(ArticalListReq req);
    //
    void deleteArtical( List<Integer> ids);


    Artical articalInfo( Integer id);




}
