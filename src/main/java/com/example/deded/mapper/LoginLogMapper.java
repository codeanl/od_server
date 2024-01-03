package com.example.deded.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deded.dto.resp.loginLog.LoginLogListResp;
import com.example.deded.pojo.Category;
import com.example.deded.pojo.LoginLog;
import com.example.deded.pojo.Menu;
import com.example.deded.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {
    //
    @Select("SELECT * FROM login_log")
    List<LoginLogListResp> loginLogList();


    @Insert("insert into login_log(uid,ip,created_at)" +
            "values(#{uid},#{ip},now())")
    void addLoginLog(LoginLog loginLog);
}
