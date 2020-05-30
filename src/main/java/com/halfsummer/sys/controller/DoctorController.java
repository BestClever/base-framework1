package com.halfsummer.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.service.DoctorService;
import com.halfsummer.sys.service.OutpatientServer;
import com.halfsummer.sys.service.UserService;
import com.halfsummer.sys.vo.DoctorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 医生模块Controller
 */
@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ResultInfo getAppointList() {
        List<Doctor> list = doctorService.list();
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(list);
    }

    @RequestMapping(value = "/find/{doctorId}")
    @ResponseBody
    public ResultInfo getAppointById(@PathVariable String doctorId) {
        Doctor doctor = doctorService.getById(doctorId);
        DoctorVo doctorVo = new DoctorVo();
        BeanUtil.copyProperties(doctor,doctorVo);
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(doctorVo);
    }

    /**
     * 医生列表(患者)
     * @param doctorVo
     * @return
     */
    @RequestMapping(value = "/doctorList")
    @ResponseBody
    public ResultInfo getdoctorList(DoctorVo doctorVo) {
        List<Doctor> list = doctorService.getdoctorList(doctorVo);
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(list);
    }



    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultInfo saveAppoint(@RequestBody DoctorVo doctorVo) {

        QueryWrapper<DoctorVo> doctorVoQueryWrapper = new QueryWrapper<>();
        boolean key = false;
        Doctor doctor  = new Doctor();
        BeanUtil.copyProperties(doctorVo,doctor);
        if (doctorVo.getDepartmentId() != null) {
            key = doctorService.updateById(doctor);
        } else {

            key = doctorService.save(doctor);
        }
        if (key) {
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }

    }



    @RequestMapping(value = "/delet")
    @ResponseBody
    public ResultInfo deletAppoint(@RequestBody DoctorVo doctorVo) {

        QueryWrapper<Doctor> doctorVoQueryWrapper = new QueryWrapper<>();
        boolean key = false;
        doctorVoQueryWrapper.eq("department_Id", doctorVo.getDepartmentId());
        key = doctorService.remove(doctorVoQueryWrapper);
        if (key) {
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }

    }


}
