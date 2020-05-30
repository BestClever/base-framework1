package com.halfsummer.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.mapper.DoctorMapper;
import com.halfsummer.sys.service.DoctorService;
import com.halfsummer.sys.vo.DoctorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author BestClever
 * @title: DoctorServiceimpl
 * @projectName base-framework
 * @description: TODO
 * @date 2020-05-23 19:31
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    @Override
    public boolean save(Doctor entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Doctor entity) {
        return false;
    }

    @Override
    public List<Doctor> getdoctorList(DoctorVo doctorVo) {
//        List<DoctorVo> doctorVos = doctorMapper.getdoctorList(doctorVo);
        return null;
    }
}
