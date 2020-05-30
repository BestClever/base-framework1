package com.halfsummer.baseframework;

import com.halfsummer.sys.mapper.DoctorMapper;
import com.halfsummer.sys.vo.DoctorVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class BaseFrameworkApplicationTests {

    @Autowired
    private DoctorMapper doctorMapper;
    @Test
    void contextLoads() {
        DoctorVo doctorVo = new DoctorVo();
        List<Map<String,Object>> doctorVos = doctorMapper.getdoctorList(doctorVo);
        doctorVos.forEach((doctor)->{
            System.out.println(doctor);
        });
    }

}
