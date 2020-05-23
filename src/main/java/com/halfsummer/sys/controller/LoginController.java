package com.halfsummer.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author BestClever
 * @title: LoginController
 * @projectName base-framework
 * @description: TODO
 * @date 2020-05-23 15:54
 */
@Controller
@RequestMapping(value = "/sys")
public class LoginController {


    @RequestMapping(value = "/toLogin")
    public String login(){
        System.out.println("sfdsfdsfdsf");
        return "sys/login";
    }
}
