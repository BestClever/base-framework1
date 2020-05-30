package com.halfsummer.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.vo.DoctorVo;

import java.util.List;
import java.util.Map;


public interface DoctorMapper extends BaseMapper<Doctor> {
    List<DoctorVo> getdoctorList(DoctorVo doctorVo);
}
