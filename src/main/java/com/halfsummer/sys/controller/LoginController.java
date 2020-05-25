package com.halfsummer.sys.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.service.UserService;
import com.halfsummer.sys.vo.Menu;
import com.halfsummer.sys.vo.UserVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BestClever
 * @title: LoginController
 * @projectName base-framework
 * @description: TODO
 * @date 2020-05-23 15:54
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userServicel;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultInfo login(HttpServletRequest request, UserVo userVo){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name",userVo.getUserName());
        User user = userServicel.getOne(userQueryWrapper);
        if (ObjectUtil.equal(user,null)) {
            ResultDataUtil.throwExcepion(CommonEnum.not_exist_user);
        }
        request.getSession().setAttribute("user",user);
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
    }

    @RequestMapping(value = "/index")
    public String toIndex(){
        return "/sys/index";
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public ResultInfo test(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        List<User> list = userServicel.list();
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(list);
    }

    @RequestMapping(value = "/navs")
    @ResponseBody
    public List getNavs(){
        List list = Lists.newArrayList();
        /**
         * private String title;//菜单名字
         *     private String href;//连接地址
         *     private String fontFamily;//字体
         *     private String icon;//图标
         *     private String spread;//是否展开
         *     private String isCheck;
         *
         *     {
         *     "title": "控制台",
         *     "href": "/sys/welcom",
         *     "fontFamily": "ok-icon",
         *     "icon": "&#xe654;",
         *     "spread": true,
         *     "isCheck": true
         *   }
         */
        Menu menu = new Menu();
        menu.setTitle("控制台");
        menu.setHref("/sys/welcom");
        menu.setFontFamily("ok-icon");
        menu.setIcon("&#xe654;");
        menu.setSpread(true);
        menu.setCheck(true);
        Menu yyxx = new Menu();
        yyxx.setTitle("预约信息");
        yyxx.setHref("/appiont/list");
        yyxx.setFontFamily("ok-icon");
        yyxx.setIcon("&#xe6b8;");
        list.add(menu);
        list.add(yyxx);
        return list;
    }
}
