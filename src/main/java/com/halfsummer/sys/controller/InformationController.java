package com.halfsummer.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Information;
import com.halfsummer.sys.service.InformationServer;
import com.halfsummer.sys.vo.DoctorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/information")
public class InformationController {
    @Autowired
    private InformationServer informationServer;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ResultInfo getInformationList() {
        List<Information> list = informationServer.list();
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(list);
    }


    @RequestMapping(value = "/update")
    @ResponseBody
    public ResultInfo saveInformation(@RequestBody Information Information) {
        QueryWrapper<Information> InformationQueryWrapper = new QueryWrapper<>();
        boolean key = false;

            key = informationServer.updateById(Information);

        if (key) {
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }

    }

    /**
     *
     */
    @RequestMapping(value = "/getClassify")
    @ResponseBody
    public ResultInfo getClassify(DoctorVo doctorVo, HttpServletRequest request) {
        Information information = informationServer.getById("1");

        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(information);


    }



}