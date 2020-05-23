package com.halfsummer.sys.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.service.UserService;
import com.halfsummer.sys.vo.UserVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResultInfo login(UserVo userVo){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name",userVo.getUserName());
        User user = userServicel.getOne(userQueryWrapper);
        if (ObjectUtil.equal(user,null)) {
            ResultDataUtil.throwExcepion(CommonEnum.not_exist_user);
        }
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
}
