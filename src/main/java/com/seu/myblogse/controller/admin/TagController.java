package com.seu.myblogse.controller.admin;

import com.seu.myblogse.service.TagService;
import com.seu.myblogse.util.PageQueryUtil;
import com.seu.myblogse.util.Result;
import com.seu.myblogse.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 篇文章可以有多个标签字段，一个标签字段也可以被标注在多个文章中，
 * 这个情况与前一个实验中的分类设计是有一些差别的，
 * 标签实体与文章实体的关系是多对多的关系，
 * 因此在表结构设计时不仅仅需要标签实体和文章实体的字段映射，还需要存储二者之间的关系数据，
 * 本系统采用的方式是新增一张关系表来维护二者多对多的关联关系。
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    public String tagPage(HttpServletRequest request) {
        request.setAttribute("path", "tags");
        return "admin/tag";
    }
    @GetMapping("/tags/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.getFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.getSuccessResult(tagService.getBlogTagPage(pageUtil));
    }
    @PostMapping("/tags/save")
    @ResponseBody
    public Result save(@RequestParam("tagName") String tagName) {
        if (StringUtils.isEmpty(tagName)) {
            return ResultGenerator.getFailResult("参数异常！");
        }
        if (tagService.saveTag(tagName)) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("标签名称重复");
        }
    }

    @PostMapping("/tags/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        /**
         * 前端也指定主键增长，与后端主键id增长保持一致，所以可以用id进行删除
         */
        if (ids.length < 1) {
            return ResultGenerator.getFailResult("参数异常！");
        }
        if (tagService.deleteBatch(ids)) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("有关联数据请勿强行删除");
        }
    }
}
