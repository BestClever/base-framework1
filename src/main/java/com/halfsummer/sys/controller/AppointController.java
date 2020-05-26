package com.halfsummer.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Appoint;
import com.halfsummer.sys.service.AppointServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("/appoint")
public class AppointController {
    @Autowired
    private AppointServer appointServer;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ResultInfo getAppointList() {
        List<Appoint> list = appointServer.list();
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(list);
    }

    @RequestMapping(value = "/find/{appointId}")
    @ResponseBody
    public ResultInfo getAppointById(@PathVariable String AppointId) {
        Appoint Appoint = appointServer.getById(AppointId);
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(Appoint);
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultInfo saveAppoint(@RequestBody Appoint Appoint) {
        QueryWrapper<Appoint> AppointQueryWrapper = new QueryWrapper<>();
        boolean key = false;
        if (Appoint.getAppointId() != null) {
            key = appointServer.updateById(Appoint);
        } else {

            key = appointServer.save(Appoint);
        }
        if (key) {
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }

    }

    @RequestMapping(value = "/delet")
    @ResponseBody
    public ResultInfo deletAppoint(@RequestBody Appoint Appoint) {
        QueryWrapper<Appoint> AppointQueryWrapper = new QueryWrapper<>();
        boolean key = false;
        AppointQueryWrapper.eq("user_id", Appoint.getAppointId());
        key = appointServer.remove(AppointQueryWrapper);
        if (key) {
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }

    }


}