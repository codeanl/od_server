package com.example.deded.service.impl;

import com.example.deded.dto.req.Banner.SaveOrUpdateBannerReq;
import com.example.deded.dto.req.artical.ArticalListReq;
import com.example.deded.dto.req.artical.SaveOrUpdateArticalReq;
import com.example.deded.dto.resp.artical.ArticalListResp;
import com.example.deded.mapper.ArticalMapper;
import com.example.deded.mapper.BannerMapper;
import com.example.deded.mapper.MemberMapper;
import com.example.deded.pojo.Artical;
import com.example.deded.pojo.Banner;
import com.example.deded.pojo.Member;
import com.example.deded.service.ArticalService;
import com.example.deded.service.BannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticalServiceImpl implements ArticalService {
    @Autowired
    private ArticalMapper articalMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public void saveOrUpdateArtical(SaveOrUpdateArticalReq req) {
        Artical artical = new Artical();
        artical.setId(req.getId());
        artical.setMid(req.getMid());
        artical.setContent(req.getContent());
        artical.setTitle(req.getTitle());
        artical.setPic(req.getPic());
        if (req.getId()>0){
            articalMapper.articalUpdate(artical);
        }else{
            articalMapper.articalAdd(artical);
        }
    }

    //
    public PageInfo<ArticalListResp> articalList(ArticalListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<ArticalListResp> articalList = articalMapper.articalList(req);
        for (ArticalListResp i: articalList ) {
            Member m=memberMapper.getMemberById(i.getMid());
            i.setMember(m);
        }
        return new PageInfo<>(articalList);
    }
    //

    @Override
    public void deleteArtical(List<Integer> ids) {
        articalMapper.deleteBatchIds(ids);
    }

    @Override
    public  Artical articalInfo( Integer id){
        return articalMapper.articalInfo(id);
    }


}
