package com.seu.myblogse.mapper;

import com.seu.myblogse.entity.AdminUser;
import org.apache.ibatis.annotations.Param;


public interface AdminMapper {

    AdminUser login(@Param("userName") String userName,@Param("password") String passWord);

    int insert(AdminUser adminUser);


}
