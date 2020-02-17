package com.seu.myblogse.service.impl;

import com.seu.myblogse.entity.AdminUser;
import com.seu.myblogse.mapper.AdminMapper;
import com.seu.myblogse.service.AdminUserService;
import com.seu.myblogse.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public AdminUser login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminMapper.login(userName,passwordMd5);
    }

    @Override
    public void register(String userName, String password, String nickName) {
        String passwordMd5=MD5Util.MD5Encode(password,"UTF-8");
        AdminUser adminUser=new AdminUser();
        adminUser.setLoginUserName(userName);
        adminUser.setLoginPassword(passwordMd5);
        adminUser.setNickName(nickName);
        adminMapper.insert(adminUser);
    }
}
