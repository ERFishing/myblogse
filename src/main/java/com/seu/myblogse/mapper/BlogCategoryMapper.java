package com.seu.myblogse.mapper;

import com.seu.myblogse.entity.BlogCategory;
import com.seu.myblogse.util.PageQueryUtil;

import java.util.List;

public interface BlogCategoryMapper {
    int getTotalCategories(PageQueryUtil pageUtil);

    BlogCategory selectByCategoryName(String categoryName);

    List<BlogCategory> findCategoryList(PageQueryUtil pageUtil);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    BlogCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);

    int deleteBatch(Integer[] ids);
}