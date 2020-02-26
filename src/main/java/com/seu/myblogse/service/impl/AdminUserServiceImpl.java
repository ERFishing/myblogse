package com.seu.myblogse.service.impl;

import com.seu.myblogse.entity.AdminUser;
import com.seu.myblogse.mapper.AdminMapper;
import com.seu.myblogse.service.AdminUserService;
import com.seu.myblogse.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminMapper adminMapper;
    // 创建一把锁
    private static final Lock USER_REGISTER_LOCK = new ReentrantLock(true);
    @Override
    public AdminUser login(String userName, String password) {
        return adminMapper.login(userName);
    }

    @Override
    public void register(String userName, String password, String nickName) {
        //加锁，防止多线程内相同用户多次注册，使用单例模式
        USER_REGISTER_LOCK.lock();
        try{
            String salt= UUID.randomUUID().toString().substring(1, 10);
            String passwordMd5=MD5Util.dbEncryption(password,salt);
            AdminUser adminUser=new AdminUser();
            adminUser.setLoginUserName(userName);
            adminUser.setLoginPassword(passwordMd5);
            adminUser.setNickName(nickName);
            adminUser.setLocked((byte) 0);
            adminUser.setSalt(salt);
            adminMapper.insert(adminUser);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            USER_REGISTER_LOCK.unlock();
        }

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
            String originalPasswordMd5 = MD5Util.dbEncryption(originalPassword, adminUser.getSalt());
            String newPasswordMd5 = MD5Util.dbEncryption(newPassword,adminUser.getSalt());
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
