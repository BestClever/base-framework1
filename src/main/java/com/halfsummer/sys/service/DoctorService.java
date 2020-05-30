package com.halfsummer.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.vo.DoctorVo;

import java.util.List;

public interface DoctorService extends IService<Doctor> {

    List<DoctorVo> getdoctorList(DoctorVo doctorVo);
}
