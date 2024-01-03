package com.example.deded.service.impl;

import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.dto.resp.loginLog.LoginLogListResp;
import com.example.deded.mapper.CategoryMapper;
import com.example.deded.mapper.LoginLogMapper;
import com.example.deded.mapper.UserMapper;
import com.example.deded.pojo.LoginLog;
import com.example.deded.pojo.Role;
import com.example.deded.pojo.User;
import com.example.deded.service.LoginLogService;
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
public class LoginLogServiceImpl  implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private UserMapper userMapper;
    public PageInfo<LoginLogListResp> loginLogList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoginLogListResp> loginLogList = loginLogMapper.loginLogList();

        for (LoginLogListResp i : loginLogList) {
            User u=userMapper.findByUserID(i.getId());
            i.setUser(u);
        }
        return new PageInfo<>(loginLogList);
    }

    //
    @Override
    public void deleteLoginLog(List<Integer> ids) {
        loginLogMapper.deleteBatchIds(ids);
    }
    @Override
    public void addLoginLog( Integer id){
        LoginLog loginLog=new LoginLog();
        loginLog.setUid(id);
        loginLog.setIp(getCurrentIp());
        loginLogMapper.addLoginLog(loginLog);
    }

    public String getCurrentIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();
        return ipAddress;
    }

}
