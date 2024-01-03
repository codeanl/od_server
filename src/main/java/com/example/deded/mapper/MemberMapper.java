package com.example.deded.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.req.member.MemberListReq;
import com.example.deded.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    //更新
    @Update("UPDATE member SET phone = #{phone}, nickname = #{nickname}, avatar = #{avatar}, email = #{email}, status = #{status} ,updated_at = NOW() WHERE id = #{id}")
    void memberUpdate(Member member);
    //
    //
    @Select({
            "<script>",
            "SELECT * FROM member",
            "<where>",
            "<if test='nickname != null and nickname != &quot;&quot;'>AND nickname LIKE CONCAT('%', #{nickname}, '%')</if>",
            "<if test='email != null and email != &quot;&quot;'>AND email LIKE CONCAT('%', #{email}, '%')</if>",
            "<if test='phone != null and phone != &quot;&quot;'>AND phone LIKE CONCAT('%', #{phone}, '%')</if>",
            "<if test='status != null and status != &quot;&quot;'>AND status=#{status}</if>",
            "</where>",
            "</script>"
    })
    List<Member> memberList(MemberListReq req);

    @Select("SELECT * FROM member where id=#{id} ")
    Member getMemberById(Integer id);


    @Insert("insert into member_login_log(uid,ip,created_at)" +
            "values(#{uid},#{ip},now())")
    void addMemberLoginLog(MemberLoginLog memberLoginLog);


    //
    @Select("SELECT * FROM member where phone=#{phone} ")
    Member findByMemberPhone(String phone);

    @Select("SELECT * FROM member_login_log where uid=#{id}")
    List<MemberLoginLog> memberLoginLog(Integer id);

}
