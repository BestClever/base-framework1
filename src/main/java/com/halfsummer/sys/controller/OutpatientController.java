package com.halfsummer.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Outpatient;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.service.OutpatientServer;
import com.halfsummer.sys.vo.OutpatientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/outpatient")
public class OutpatientController {
    @Autowired
    private OutpatientServer outpatientServer;

    @RequestMapping(value = "/index")
    public String index(){
        return "outpatient/index";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public ResultInfo getOutpatientList() {
        List<Outpatient> list = outpatientServer.list();
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(list);
    }

    @RequestMapping(value = "/find/{outpatientId}")
    @ResponseBody
    public ResultInfo getOutpatientById(@PathVariable String OutpatientId) {
        Outpatient Outpatient = outpatientServer.getById(OutpatientId);
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(Outpatient);
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultInfo saveOutpatient(@RequestBody OutpatientVo outpatientVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //获取当前
        Outpatient outpatient = new  Outpatient();
        BeanUtil.copyProperties(outpatientVo,outpatient);
        boolean key = false;
        if (outpatientVo.getOutpatientId() != null) {
            key = outpatientServer.updateById(outpatient);
        } else {
            outpatient.setDoctorId(user.getUserId());
            outpatient.setDoctorName(user.getUserName());
            key = outpatientServer.save(outpatient);
        }
        if (key) {
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }

    }

    @RequestMapping(value = "/delet")
    @ResponseBody
    public ResultInfo deletOutpatient(@RequestBody Outpatient Outpatient) {
        QueryWrapper<Outpatient> OutpatientQueryWrapper = new QueryWrapper<>();
        boolean key = false;
        OutpatientQueryWrapper.eq("outpatient_id", Outpatient.getOutpatientId());
        key = outpatientServer.remove(OutpatientQueryWrapper);
        if (key) {
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }

    }



}