package com.halfsummer.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.vo.DoctorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface DoctorMapper extends BaseMapper<Doctor> {
    List<DoctorVo> getdoctorList(DoctorVo doctorVo);

    /**
     * 自定义sql分页
     * @param page
     * @param queryWrapper 看这里看这里，如果自定义的方法中需要用到wrapper查询条件，需要这样写
     * @return
     */
    IPage<DoctorVo> selectMyPage(IPage<DoctorVo> page, @Param(Constants.WRAPPER) Wrapper<DoctorVo> queryWrapper);
}
