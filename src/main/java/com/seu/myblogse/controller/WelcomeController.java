package com.seu.myblogse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 博客首页欢迎页面，引导注册或登录，或进入展示页
 */
@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String welcome() {
        return "admin/welcome";
    }
}
