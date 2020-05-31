package com.halfsummer.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.result.DataGridResultInfo;
import com.halfsummer.baseframework.result.PageInfo;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.mapper.DoctorMapper;
import com.halfsummer.sys.service.DoctorService;
import com.halfsummer.sys.vo.DoctorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/doctor")
    public String doctor(){
        return "/doctor/doctor";
    }

    @RequestMapping(value = "/work")
    public String work(){
        return "/doctor/work";
    }

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
    public DataGridResultInfo getdoctorList( DoctorVo doctorVo) {

        QueryWrapper<DoctorVo> wrapper = new QueryWrapper<DoctorVo>();
        wrapper.eq("role_code","2");
        if (StrUtil.isNotBlank(doctorVo.getDepartmentName())){
            wrapper.like("u.user_name",doctorVo.getDepartmentName());
        }
        if (StrUtil.isNotBlank(doctorVo.getOutpatientDate() )){
            wrapper .eq("outpatient_date",doctorVo.getOutpatientDate());
        }
        if (StrUtil.isNotBlank(doctorVo.getDepartmentProfile())){
            wrapper.like("d.department_name",doctorVo.getDepartmentProfile());
        }
        Page<DoctorVo> page = new Page<DoctorVo>(doctorVo.page,doctorVo.size);
        IPage<DoctorVo> doctorVoIPage = doctorMapper.selectMyPage(page, wrapper);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(doctorVoIPage.getRecords());
        pageInfo.setTotal(doctorVoIPage.getTotal());
        return  ResultDataUtil.createQueryResult(pageInfo);

    }

    /**
     * 预约查询接口(医生)
     * @param doctorVo
     * @return
     */
    @RequestMapping(value = "/checkToAppoint")
    @ResponseBody
    public DataGridResultInfo checkToAppointment( DoctorVo doctorVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        PageInfo pageInfo = new PageInfo();
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
