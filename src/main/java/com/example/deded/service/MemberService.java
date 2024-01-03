package com.example.deded.service;

import com.example.deded.dto.req.member.MemberListReq;
import com.example.deded.dto.req.member.UpdateMemberReq;
import com.example.deded.dto.req.role.RoleUpdateReq;
import com.example.deded.pojo.Member;
import com.example.deded.pojo.MemberLoginLog;
import com.example.deded.pojo.Role;
import com.example.deded.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MemberService {
    //
    void updateMember(UpdateMemberReq req);
    //
    PageInfo<Member> memberList(MemberListReq  req);
    //
    void deleteMember( List<Integer> ids);

    Member findByMemberPhone(String phone);

    void addMemberLoginLog( Integer id);
    List<MemberLoginLog> memberLoginLog(Integer id);
}
