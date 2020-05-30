package com.halfsummer.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.enums.ParameterEnum;
import com.halfsummer.baseframework.result.DataGridResultInfo;
import com.halfsummer.baseframework.result.PageInfo;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Appoint;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.domain.Outpatient;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.mapper.AppointMapper;
import com.halfsummer.sys.service.AppointServer;
import com.halfsummer.sys.service.OutpatientServer;
import com.halfsummer.sys.vo.AppointVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/appoint")
public class AppointController {
    @Autowired
    private AppointServer appointServer;
    @Autowired
    private OutpatientServer outpatientServer;

    @Autowired
    private AppointMapper appointMapper;

    @RequestMapping(value = "/list")
    @ResponseBody
    public DataGridResultInfo getAppointList() {
        QueryWrapper<Appoint> wrapper = new QueryWrapper<>();
        Page<Appoint> appointPage = appointMapper.selectPage(new Page<Appoint>(1, 2), wrapper);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(appointPage.getRecords());
        pageInfo.setTotal(appointPage.getTotal());

        return  ResultDataUtil.createQueryResult(pageInfo);
    }

    @RequestMapping(value = "/find/{appointId}")
    @ResponseBody
    public ResultInfo getAppointById(@PathVariable String AppointId) {
        Appoint Appoint = appointServer.getById(AppointId);
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(Appoint);
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultInfo saveAppoint(@RequestBody AppointVo appointVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Appoint appoint = new Appoint();
        BeanUtil.copyProperties(appointVo,appoint);
        appoint.setUserId(user.getUserId());
        appoint.setUserName(user.getUserName());
        appoint.setPatientType("1");
        boolean key = false;
        if (appoint.getAppointId() != null) {
            key = appointServer.updateById(appoint);
        } else {
            appoint.setAppointId(IdUtil.simpleUUID());
            appoint.setAppointStage(ParameterEnum.OUTPATIENT_SERVICE.getResultMsg());
            key = appointServer.save(appoint);
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

    /**
     * 付款接口
     */

    @RequestMapping(value = "/payment")
    @ResponseBody
    public ResultInfo payment(@RequestBody AppointVo appointVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        appointVo.setAppointStatus("2");
        appointVo.setCancelId(user.getUserId());
        appointVo.setCancelName(user.getUserName());
       Appoint appoint = new Appoint();
        BeanUtil.copyProperties(appointVo,appoint);
        boolean key = appointServer.updateById(appoint);

        if (key) {
//            预约成功后增加当前预约人数
            appoint = appointServer.getById(appoint.appointId);
            Outpatient outpatient = outpatientServer.getById(appoint.getOutpatientId());
            outpatient.setOutpatientNumber(String.valueOf(Integer.valueOf(outpatient.getOutpatientNumber())+1));
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }
    }



}