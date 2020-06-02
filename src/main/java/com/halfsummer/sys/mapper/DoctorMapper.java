package com.halfsummer.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.domain.Outpatient;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.vo.AppointVo;
import com.halfsummer.sys.vo.DoctorVo;
import com.halfsummer.sys.vo.OutpatientVo;
import com.halfsummer.sys.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DoctorMapper extends BaseMapper<Doctor> {
    List<DoctorVo> getdoctorList(DoctorVo doctorVo);

    /**
     * 自定义sql分页
     * @param page
     * @param queryWrapper 看这里看这里，如果自定义的方法中需要用到wrapper查询条件，需要这样写
     * @return
     */
    IPage<DoctorVo> selectMyPage(IPage<DoctorVo> page, @Param(Constants.WRAPPER) Wrapper<DoctorVo> queryWrapper);

    IPage<OutpatientVo> checkToAppointment(IPage<OutpatientVo> page,  @Param(Constants.WRAPPER) Wrapper<OutpatientVo> queryWrapper);

    List<Outpatient> getDatelist(User user);

    IPage<UserVo> userList(IPage<UserVo> page);

    IPage<AppointVo> docResults(IPage<AppointVo> page, @Param(Constants.WRAPPER)Wrapper<AppointVo> wrapper);
}
