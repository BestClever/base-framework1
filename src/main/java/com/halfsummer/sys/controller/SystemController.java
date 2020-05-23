package com.halfsummer.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author BestClever
 * @title: SysController
 * @projectName base-framework
 * @description: TODO
 * @date 2020-05-23 21:18
 */
@Controller
@RequestMapping(value = "/sys")
public class SystemController {
    @RequestMapping(value = "/toLogin")
    public String login(){
        return "sys/login";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "/sys/index";
    }

    @RequestMapping(value = "/welcom")
    public String welcom(){
        return "/sys/console";
    }
}
