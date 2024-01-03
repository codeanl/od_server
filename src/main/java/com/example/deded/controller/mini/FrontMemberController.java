package com.example.deded.controller.mini;

import com.example.deded.dto.req.member.MemberLoginReq;
import com.example.deded.dto.req.user.LoginReq;
import com.example.deded.dto.resp.member.MemberLoginResp;
import com.example.deded.pojo.Member;
import com.example.deded.pojo.Result;
import com.example.deded.pojo.User;
import com.example.deded.service.MemberService;
import com.example.deded.utils.JwtUtil;
import com.example.deded.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("front/member")

public class FrontMemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public Result<MemberLoginResp> login(@RequestBody MemberLoginReq req) {
        //根据用户名查询用户
        Member u = memberService.findByMemberPhone(req.getPhone());
        System.out.println(u);
        //判断该用户是否存在
        if (u == null) {
            return Result.error("用户不存在");
        }
        if (u.getStatus() == "0") {
            return Result.error("用户已被锁定");
        }
        if(req.getPassword().equals(u.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("phone", u.getPhone());
            String token = JwtUtil.genTokenMember(claims);
            memberService.addMemberLoginLog(u.getId());
            //
            MemberLoginResp memberLoginResp=new MemberLoginResp();
            memberLoginResp.setId(u.getId());
            memberLoginResp.setPhone(u.getPhone());
            memberLoginResp.setNickname(u.getNickname());
            memberLoginResp.setAvatar(u.getAvatar());
            memberLoginResp.setEmail(u.getEmail());
            memberLoginResp.setIntro(u.getIntro());
            memberLoginResp.setStatus(u.getStatus());
            memberLoginResp.setToken(token);
            return Result.success(memberLoginResp);
        }else{
            return Result.error("密码错误");
        }

    }
}
