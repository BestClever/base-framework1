package com.halfsummer.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.result.DataGridResultInfo;
import com.halfsummer.baseframework.result.PageInfo;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.mapper.DoctorMapper;
import com.halfsummer.sys.service.DoctorService;
import com.halfsummer.sys.vo.DoctorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 医生模块Controller
 */
@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorMapper doctorMapper;

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
    public DataGridResultInfo getdoctorList(@RequestBody DoctorVo doctorVo) {
        QueryWrapper<DoctorVo> wrapper = new QueryWrapper<DoctorVo>();
        wrapper.eq("role_code","2");
        if (doctorVo.getDepartmentName() !=null){
            wrapper.eq("user_name",doctorVo.getDepartmentName());
        }
        if (doctorVo.getOutpatientDate() != null){
            wrapper .eq("outpatient_date",doctorVo.getOutpatientDate());
        }
        if (doctorVo.getDepartmentProfile() != null){
            wrapper.eq("department_name",doctorVo.getDepartmentProfile());
        }
        Page<DoctorVo> page = new Page<DoctorVo>(Long.valueOf(doctorVo.page),Long.valueOf(doctorVo.size));
        IPage<DoctorVo> doctorVoIPage = doctorMapper.selectMyPage(page, wrapper);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(doctorVoIPage.getRecords());
        pageInfo.setTotal(doctorVoIPage.getTotal());
        return  ResultDataUtil.createQueryResult(pageInfo);

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
