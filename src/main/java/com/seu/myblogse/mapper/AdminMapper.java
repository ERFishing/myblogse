package com.seu.myblogse.mapper;

import com.seu.myblogse.entity.AdminUser;
import org.apache.ibatis.annotations.Param;


public interface AdminMapper {

    AdminUser login(@Param("userName") String userName);

    int insert(AdminUser adminUser);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

}
