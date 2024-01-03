package com.example.deded.controller.admin;


import com.example.deded.dto.req.member.MemberListReq;
import com.example.deded.dto.req.member.UpdateMemberReq;
import com.example.deded.dto.req.role.RoleListReq;
import com.example.deded.dto.req.role.RoleUpdateReq;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.pojo.Result;
import com.example.deded.pojo.RoleMenu;
import com.example.deded.service.MemberService;
import com.example.deded.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 更新会员
    @PutMapping("/update")
    public Result memberUpdate(@RequestBody UpdateMemberReq req) {
        memberService.updateMember(req);
        return Result.success();
    }
    // 删除会员
    @PostMapping("/del")
    public Result deleteMember(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        memberService.deleteMember(ids);
        return Result.success();
    }
    //会员列表
    @GetMapping("/list")
    public Result roleList(@ModelAttribute MemberListReq req) {
        return Result.success(memberService.memberList(req));
    }

    @GetMapping("/memberLoginLog")
    public Result memberLoginLog(Integer id) {
        return Result.success(memberService.memberLoginLog(id));
    }
}
