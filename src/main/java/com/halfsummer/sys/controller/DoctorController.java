package com.halfsummer.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.enums.ParameterEnum;
import com.halfsummer.baseframework.result.DataGridResultInfo;
import com.halfsummer.baseframework.result.PageInfo;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.domain.Outpatient;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.mapper.DoctorMapper;
import com.halfsummer.sys.service.AutoincremenServer;
import com.halfsummer.sys.service.DoctorService;
import com.halfsummer.sys.service.OutpatientServer;
import com.halfsummer.sys.service.UserService;
import com.halfsummer.sys.vo.DoctorVo;
import com.halfsummer.sys.vo.OutpatientVo;
import com.halfsummer.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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
    @Autowired
    private UserService userService;
    @Autowired
    private OutpatientServer outpatientServer;
    @Autowired
    private AutoincremenServer autoincremenServer;

    @RequestMapping(value = "/doctor")
    public String doctor(){
        return "/doctor/doctor";
    }
    ///doctor/huiz


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
     * 注册接口
     */
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public ResultInfo saveUser(UserVo userVo) {
        User user = new User();
        BeanUtil.copyProperties(userVo,user);
        user.setUserId(IdUtil.simpleUUID());
        user.setRoleCode("1");
        user.setRoleName("病人");
        user.setUserSex("1");
        user.setBirthDay(DateUtil.formatLocalDateTime(LocalDateTime.now()));
        userService.save(user);

        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);


    }


    /**
     * 医生列表(患者)
     * @param doctorVo
     * @return
     */
    @RequestMapping(value = "/doctorList")
    @ResponseBody
    public DataGridResultInfo getdoctorList( DoctorVo doctorVo) {
        doctorVo.setOutpatientDate(DateUtil.today());
        QueryWrapper<DoctorVo> wrapper = new QueryWrapper<DoctorVo>();
        wrapper.eq("role_code","2");
        if (StrUtil.isNotBlank(doctorVo.getDepartmentName())){
            wrapper.like("u.user_name",doctorVo.getDepartmentName());
        }
        if (StrUtil.isNotBlank(doctorVo.getOutpatientDate() )){
            wrapper .eq("outpatient_date",doctorVo.getOutpatientDate());
        }
        wrapper.isNull("a.appoint_id");
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




    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultInfo saveAppoint( DoctorVo doctorVo) {

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
    public ResultInfo deletAppoint( DoctorVo doctorVo) {

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


    /**
     * 预约查询接口(医生)
     * @param doctorVo
     * @return
     */
    @RequestMapping(value = "/checkToAppoint")
    @ResponseBody
    public DataGridResultInfo checkToAppointment(DoctorVo doctorVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        QueryWrapper<OutpatientVo> wrapper = new QueryWrapper<>();
        wrapper.ne("appoint_status","1").isNotNull("appoint_id");
        if (StrUtil.isNotBlank(doctorVo.getDepartmentName())){
            wrapper.like("u.user_name",doctorVo.getDepartmentName());
        }
        if (StrUtil.isNotBlank(doctorVo.getOutpatientDate() )){
            wrapper .eq("outpatient_date",doctorVo.getOutpatientDate());
        }
        Page<OutpatientVo> page = new Page<OutpatientVo>(doctorVo.page,doctorVo.size);
        IPage<OutpatientVo> doctorVoIPage = doctorMapper.checkToAppointment(page, wrapper);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(doctorVoIPage.getRecords());
        pageInfo.setTotal(doctorVoIPage.getTotal());
        return  ResultDataUtil.createQueryResult(pageInfo);
    }



    /**
     * 获取可回诊时间
     */
    @RequestMapping(value = "/getDate")
    @ResponseBody
    public ResultInfo getDate( HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");



        List<Outpatient> list = doctorMapper.getDatelist(user);

        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(list);


    }

    //

    /**
     * 医生信息展示
     */
    @RequestMapping(value = "/information")
    @ResponseBody
    public ResultInfo information( HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

       user = userService.getById(user.getUserId());
        QueryWrapper<Outpatient> wrapper = new QueryWrapper<>();
        Outpatient outpatient = outpatientServer.getOne(wrapper.eq("doctor_id",user.getUserId()).eq("outpatient_date", DateUtil.today()));
       DoctorVo doctorVo = new DoctorVo();
       doctorVo.setDepartmentName(user.getUserName());
       if (outpatient != null){

           doctorVo.setOutpatientNumber(outpatient.getOutpatientNumber());
       }else {
           doctorVo.setOutpatientNumber(ParameterEnum.UNPUBLISHED_WORK.getResultMsg());
       }
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(doctorVo);


    }
    /**
     * 门诊数量修改
     */
    @RequestMapping(value = "/modifyNumber")
    @ResponseBody
    public ResultInfo modifyNumber(DoctorVo doctorVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        user = userService.getById(user.getUserId());
        QueryWrapper<Outpatient> wrapper = new QueryWrapper<>();
        Outpatient outpatient = outpatientServer.getOne(wrapper.eq("doctor_id",user.getUserId()).eq("outpatient_date", DateUtil.today()));
        outpatient.setOutpatientNumber(doctorVo.getOutpatientNumber());
        outpatientServer.updateById(outpatient);

        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);


    }

    /**
     * 用户列表(管理员用)
     */

    @RequestMapping(value = "/userList")
    @ResponseBody
    public DataGridResultInfo userList(DoctorVo doctorVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        QueryWrapper<UserVo> wrapper = new QueryWrapper<>();

        Page<UserVo> page = new Page<UserVo>(doctorVo.page,doctorVo.size);
        IPage<UserVo> doctorVoIPage = doctorMapper.userList(page);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(doctorVoIPage.getRecords());
        pageInfo.setTotal(doctorVoIPage.getTotal());
        return  ResultDataUtil.createQueryResult(pageInfo);
    }
    /**
     * 用户入职
     */
    @RequestMapping(value = "/induction")
    @ResponseBody
    public ResultInfo induction(UserVo userVo) {

       User user = userService.getById(userVo.getUserId());
       user.setRoleCode("2");
       user.setRoleName("医生");
        userService.updateById(user);
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);


    }

}
