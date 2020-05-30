package com.halfsummer.baseframework;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.mapper.DoctorMapper;
import com.halfsummer.sys.vo.DoctorVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BaseFrameworkApplicationTests {

    @Autowired
    private DoctorMapper doctorMapper;
    @Test
    void contextLoads() {
        DoctorVo doctorVo = new DoctorVo();
        List<DoctorVo> doctorVos = doctorMapper.getdoctorList(doctorVo);
        doctorVos.forEach((doctor)->{
            System.out.println(doctor.getDepartmentName());
        });
    }

    @Test
    void selecPageDemo(){
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        Page<Doctor> doctorPage = doctorMapper.selectPage(new Page<Doctor>(1, 2), wrapper);
        System.out.println(doctorPage.getTotal()+":"+doctorPage.getRecords());
    }

}
