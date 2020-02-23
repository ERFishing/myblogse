package com.seu.myblogse.service;

import com.seu.myblogse.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
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
    /**
     * 获取用户信息
     *
     * @param loginUserId
     * @return
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 修改当前登录用户的密码
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    /**
     * 修改当前登录用户的名称信息
     *
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);

}
