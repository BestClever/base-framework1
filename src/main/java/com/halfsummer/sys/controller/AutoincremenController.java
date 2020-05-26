package com.halfsummer.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Autoincremen;
import com.halfsummer.sys.service.AutoincremenServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/autoincremen")
public class AutoincremenController {
    @Autowired
    private AutoincremenServer autoincremenServer;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ResultInfo getAutoincremenList() {
        List<Autoincremen> list = autoincremenServer.list();
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(list);
    }


    @RequestMapping(value = "/update")
    @ResponseBody
    public ResultInfo saveAutoincremen(@RequestBody Autoincremen Autoincremen) {
        QueryWrapper<Autoincremen> AutoincremenQueryWrapper = new QueryWrapper<>();
        boolean key = false;

            key = autoincremenServer.updateById(Autoincremen);

        if (key) {
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }

    }



}