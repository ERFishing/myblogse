package com.seu.myblogse.service;

import com.seu.myblogse.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserService {
    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(String userName, String password);

    /**
     * 用户注册
     * @param userName
     * @param password
     * @param nickName
     * @return
     */
    void register(String userName, String password,String nickName);
}
