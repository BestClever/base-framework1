package com.halfsummer.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "sys/login";
    }

    @RequestMapping(value = "/register")
    public String register(){
        return "/sys/register";
    }
    @RequestMapping(value = "/information")
    public String information(){
        return "/sys/information";
    }
}
