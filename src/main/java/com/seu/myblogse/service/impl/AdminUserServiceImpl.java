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
        adminUser.setLocked((byte) 0);
        adminMapper.insert(adminUser);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //比较原密码是否正确
            if (originalPasswordMd5.equals(adminUser.getLoginPassword())) {
                //设置新密码并修改
                adminUser.setLoginPassword(newPasswordMd5);
                if (adminMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                    //修改成功则返回true
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //设置新密码并修改
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }
}
