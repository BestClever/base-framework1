package com.halfsummer.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.enums.ParameterEnum;
import com.halfsummer.baseframework.result.DataGridResultInfo;
import com.halfsummer.baseframework.result.PageInfo;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Appoint;
import com.halfsummer.sys.domain.Outpatient;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.mapper.AppointMapper;
import com.halfsummer.sys.service.AppointServer;
import com.halfsummer.sys.service.DoctorService;
import com.halfsummer.sys.service.OutpatientServer;
import com.halfsummer.sys.service.UserService;
import com.halfsummer.sys.vo.AppointVo;
import com.halfsummer.sys.vo.EmrVo;
import com.halfsummer.sys.vo.LocationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/appoint")
public class AppointController {
    @Autowired
    private AppointServer appointServer;
    @Autowired
    private OutpatientServer outpatientServer;

    @Autowired
    private AppointMapper appointMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/index")
    public String index(){
        return "/outpatient/appointIndex";
    }

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
    public ResultInfo saveAppoint(AppointVo appointVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Appoint appoint = new Appoint();
        BeanUtil.copyProperties(appointVo,appoint);
        Outpatient outpatient = outpatientServer.getById(appoint.getOutpatientId());
        appoint.setAppointDate(outpatient.getOutpatientDate());
        appoint.setAppointStageName(outpatient.getOutpatientDate());
        appoint.setUserId(user.getUserId());
        appoint.setUserName(user.getUserName());
        appoint.setAppointStatus("1");
        boolean key = false;
        if (StrUtil.isNotBlank(appoint.getAppointId())) {
            key = appointServer.updateById(appoint);
        } else {
            appoint.setAppointId(IdUtil.simpleUUID());
//            appoint.setAppointDate(DateUtil.parse(appointVo.getAppointDate(), "yyyy-MM-dd"));
            appoint.setPatientType(ParameterEnum.OUTPATIENT_SERVICE.getResultMsg());

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
        AppointQueryWrapper.eq("appoint_id", Appoint.getAppointId());
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
    public ResultInfo payment(AppointVo appointVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int num ;
        appointVo.setAppointStatus("2");
        appointVo.setUserId(user.getUserId());
        appointVo.setUsername(user.getUserName());
        Appoint appoint = new Appoint();
        BeanUtil.copyProperties(appointVo,appoint);
        QueryWrapper<Appoint> queryWrapper = new QueryWrapper<>();
        //根据门诊号查询对应的流程号
        Appoint as = appointServer.getOne(queryWrapper.eq("outpatient_id",appoint.getOutpatientId()).eq("user_id",user.getUserId()));
        List<Appoint> list = appointServer.list(queryWrapper.eq("outpatient_id",appoint.getOutpatientId()).eq("appoint_status","2"));
        if (list == null){
            num = 1;
        }else {
            num = list.size()+1;
        }
        appoint.setLocation(String.valueOf(num));
        appoint.setAppointId(as.getAppointId());
        boolean key = appointServer.updateById(appoint);
        if (key) {
//            预约成功后增加当前预约人数
            appoint = appointServer.getById(appoint.appointId);
            Outpatient outpatient = outpatientServer.getById(appoint.getOutpatientId());
            outpatient.setCurrentNum(String.valueOf(Integer.valueOf(outpatient.getCurrentNum())+1));
            outpatientServer.updateById(outpatient);
            return ResultDataUtil.createSuccess(CommonEnum.MONEY);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }
    }
    /**
     * 取消预约
     */
    @RequestMapping(value = "/aCancellation")
    @ResponseBody
    public ResultInfo aCancellation( AppointVo appointVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        appointVo.setAppointStatus("5");
        appointVo.setCancelId(user.getUserId());
        appointVo.setCancelName(user.getUserName());
        Appoint appoint = new Appoint();
        BeanUtil.copyProperties(appointVo,appoint);
//        QueryWrapper<Appoint> queryWrapper = new QueryWrapper<>();
//        List<Appoint> list = appointServer.list(queryWrapper.eq("outpatient_id",appoint.getOutpatientId()).eq("appoint_status","2"));
        boolean key = appointServer.updateById(appoint);
        if (key) {
//            预约成功后增加当前预约人数
            appoint = appointServer.getById(appoint.appointId);
            Outpatient outpatient = outpatientServer.getById(appoint.getOutpatientId());
            outpatient.setCurrentNum(String.valueOf(Integer.valueOf(outpatient.getCurrentNum())-1));
            outpatientServer.updateById(outpatient);
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }
    }

    /**
     * 完成诊断 To complete the booking
     */
    @RequestMapping(value = "/cbook")
    @ResponseBody
    public ResultInfo completeyTheBook(AppointVo appointVo) {
        appointVo.setAppointStatus(appointVo.getAppointStatus());
        Appoint appoint = new Appoint();
        BeanUtil.copyProperties(appointVo,appoint);
        boolean key = appointServer.updateById(appoint);
        if (appointVo.getAppointStatus() =="4") {
//            成功后减少当前预约人数
            appoint = appointServer.getById(appoint.appointId);
            Outpatient outpatient = outpatientServer.getById(appoint.getOutpatientId());
            if (StrUtil.isNotBlank(outpatient.getCurrentNum()) && Integer.valueOf(outpatient.getCurrentNum())>0){
                outpatient.setCurrentNum(String.valueOf(Integer.valueOf(outpatient.getCurrentNum())-1));
            }
            outpatient.setCurrentNum("0");
            outpatientServer.updateById(outpatient);
            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
        } else {
            return ResultDataUtil.createSuccess(CommonEnum.SAVE_FAILED);
        }
    }

    /**
     *    排队接口
     */
    @RequestMapping(value = "/lineUp")
    @ResponseBody
    public ResultInfo lineUp( HttpServletRequest request) {
        QueryWrapper<Appoint> queryWrapper = new QueryWrapper<>();
        LocationVo locationVo = new LocationVo() ;
        User user = (User) request.getSession().getAttribute("user");
        String today= DateUtil.today();
//        String today= "2020-05-30"; // TODO: 2020-05-31 0031
        Appoint appoint = appointServer.getOne(queryWrapper.eq("appoint_date",today).eq("user_id",user.getUserId()).eq("appoint_status","2"));

        if (appoint != null){
            Outpatient outpatient = outpatientServer.getById(appoint.getOutpatientId());
            //进行队列筛选
            queryWrapper= new QueryWrapper<>();
            List<Appoint> list = appointServer.list(queryWrapper
                    .eq("outpatient_id",appoint.getOutpatientId())
                    .eq("appoint_date",appoint.getAppointDate())
                    .eq("appoint_status","2")
                    .lt("location",appoint.getLocation())
                    .orderByAsc("location")
            );
            if (list == null){
                //是为队伍第一位
                locationVo.setSwiTch("1");
            }else {
                locationVo.setPeopleNum(String.valueOf(list.size()));
                int time = Integer.valueOf(list.size()) * Integer.valueOf(ParameterEnum.WAITING_TIME.getResultMsg());
                int hours = (int) Math.floor(time / 60);
                int minute = time % 60;
                String swTiem = "";
                if (hours<1){
                    swTiem = minute+" 分钟 ";
                }else {
                    swTiem = hours+" 小时 "+minute+" 分钟 ";
                }
                locationVo.setDoctorName(outpatient.getDoctorName());
                locationVo.setAppointDate(appoint.getAppointDate());
                locationVo.setWaitingTime(swTiem);
            }

            return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(locationVo);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DATA_DOESNT_EXIST);
    }


    //患者电子病历接口
    @RequestMapping(value = "/getEMR")
    @ResponseBody
    public ResultInfo getEMR( HttpServletRequest request) {
        QueryWrapper<Appoint> queryWrapper = new QueryWrapper<>();
        LocationVo locationVo = new LocationVo() ;
        User user = (User) request.getSession().getAttribute("user");
        String today= DateUtil.today();
         user  = userService.getById(user.getUserId());
         Appoint appoint = appointMapper.selectOne(queryWrapper.eq("user_id",user.getUserId()).eq("appoint_date",today));
        EmrVo emrVo = new EmrVo();
         BeanUtil.copyProperties(user,emrVo);
         BeanUtil.copyProperties(appoint,emrVo);

        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(emrVo);
    }

    //回诊接口
    @RequestMapping(value = "/returnVisit")
    @ResponseBody
    public ResultInfo returnVisit( AppointVo appointVo,HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //当前预约完成
        Appoint appoint = new Appoint();
        int num;

       appoint.setAppointStatus("4");
       appoint.setMedicalAdvice(appointVo.getMedicalAdvice());
       appointServer.updateById(appoint);
       appoint = appointServer.getById(appointVo.getAppointId());
       //根据医生 id 与日期查出对应的门诊单号
        QueryWrapper<Outpatient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("doctor_id",user.getUserId()).eq("outpatient_date",appointVo.getAppointDate());
        Outpatient outpatient = outpatientServer.getOne(queryWrapper);
        if (outpatient != null){
            appoint.setAppointStatus("6");
            appoint.setOutpatientId(outpatient.outpatientId);
            appoint.setAppointDate(appointVo.getAppointDate());
            appoint.setAppointStageName(appointVo.getAppointDate());
            QueryWrapper<Appoint> queryWrappers = new QueryWrapper<>();
            List<Appoint> list = appointServer.list(queryWrappers.eq("outpatient_id",appoint.getOutpatientId()).eq("appoint_status","2"));
            if (list == null){
                num = 1;
            }else {
                num = list.size()+1;
            }
            appoint.setLocation(String.valueOf(num));
            appoint.setAppointId(IdUtil.simpleUUID());
            appoint.setPatientType(ParameterEnum.RETURN_VISIT.getResultMsg());
            appointServer.save(appoint);
        }
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS) ;
    }

    /**
     * 返回医嘱
     */
    @RequestMapping(value = "/getMedicalAdvice")
    @ResponseBody
    public ResultInfo getMedicalAdvice(  HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        QueryWrapper<Appoint> queryWrappers = new QueryWrapper<>();
        queryWrappers.eq("user_id",user.getUserId()).eq("appoint_date", DateUtil.today());
        Appoint appoint = appointServer.getOne(queryWrappers);
        if (appoint == null){
            appoint = new Appoint();
            appoint.setMedicalAdvice("等待医生填写");
        }


        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS).setData(appoint) ;
    }





}