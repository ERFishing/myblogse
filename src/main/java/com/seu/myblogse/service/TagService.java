package com.seu.myblogse.service;

import com.seu.myblogse.entity.BlogTagCount;
import com.seu.myblogse.util.PageQueryUtil;
import com.seu.myblogse.util.PageResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface TagService {
    /**
     * 查询标签的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    int getTotalTags();

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();
}
