package com.example.deded.service.impl;

import com.example.deded.dto.req.member.MemberListReq;
import com.example.deded.dto.req.member.UpdateMemberReq;
import com.example.deded.mapper.MemberMapper;
import com.example.deded.pojo.Member;
import com.example.deded.pojo.MemberLoginLog;
import com.example.deded.pojo.Role;
import com.example.deded.pojo.User;
import com.example.deded.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public void updateMember(UpdateMemberReq req) {
        Member member = new Member();
        member.setId(req.getId());
        member.setPhone(req.getPhone());
        member.setNickname(req.getNickname());
        member.setAvatar(req.getAvatar());
        member.setEmail(req.getEmail());
        member.setStatus(req.getStatus());
        memberMapper.memberUpdate(member);
    }
    //
    //
    public PageInfo<Member> memberList(MemberListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<Member> memberList = memberMapper.memberList(req);
        return new PageInfo<>(memberList);
    }

    //
    @Override
    public void deleteMember(List<Integer> ids) {
        memberMapper.deleteBatchIds(ids);
    }


    @Override
    public void addMemberLoginLog( Integer id){
        MemberLoginLog memberLoginLog=new MemberLoginLog();
        memberLoginLog.setUid(id);
        memberLoginLog.setIp(getCurrentIp());
        memberMapper.addMemberLoginLog(memberLoginLog);
    }
    public String getCurrentIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();
        return ipAddress;
    }

    @Override
    public Member findByMemberPhone(String phone) {
        Member u = memberMapper.findByMemberPhone(phone);
        return u;
    }
    @Override
    public List<MemberLoginLog> memberLoginLog( Integer id){
        return  memberMapper.memberLoginLog(id);
    }
}
