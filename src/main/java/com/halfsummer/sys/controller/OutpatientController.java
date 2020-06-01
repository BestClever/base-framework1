package com.halfsummer.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsummer.baseframework.enums.CommonEnum;
import com.halfsummer.baseframework.enums.ParameterEnum;
import com.halfsummer.baseframework.result.ResultDataUtil;
import com.halfsummer.baseframework.result.ResultInfo;
import com.halfsummer.sys.domain.Outpatient;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.service.DepartmentServer;
import com.halfsummer.sys.service.OutpatientServer;
import com.halfsummer.sys.service.UserService;
import com.halfsummer.sys.vo.OutpatientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/outpatient")
public class OutpatientController {
    @Autowired
    private OutpatientServer outpatientServer;
    @Autowired
    private DepartmentServer departmentServer;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "administrator/administrator";
    }
//    @RequestMapping(value = "/doctor")
//    public String doctor(){
//        return "outpatient/doctor";
//    }

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

    /**
     * 发布工作
     */
    @RequestMapping(value = "/publishedWork")
    @ResponseBody
    public ResultInfo publishedWork( HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Outpatient outpatient = new Outpatient();
        outpatient.setDoctorId(user.getUserId());
        outpatient.setDoctorName(user.getUserName());
        user = userService.getById(user.getUserId());
//        user = userService.getById("124");
        outpatient.setDeptId(user.getDepartmentId());
        outpatient.setOutpatientNumber(ParameterEnum.DEFAULT_NUMBER.getResultMsg());
        outpatient.setOutpatientNotice(ParameterEnum.DEFAULT_WORK.getResultMsg());
        List<String> list = findDates(DateUtil.date(),DateUtil.nextWeek());
        for (int i=0;i<list.size();i++){
            outpatient.setOutpatientId(IdUtil.simpleUUID());
            outpatient.setOutpatientDate(list.get(i));
            outpatientServer.save(outpatient);
        }
        return ResultDataUtil.createSuccess(CommonEnum.SUCCESS);
    }


    public static List<String> findDates(Date dBegin, Date dEnd)
    {
        List lDate = new ArrayList();
        lDate.add( DateUtil.formatDate(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add( DateUtil.formatDate(calBegin.getTime()));
        }
        return lDate;
    }



}