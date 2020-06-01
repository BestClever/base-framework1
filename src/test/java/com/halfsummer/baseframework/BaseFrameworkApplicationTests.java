package com.halfsummer.baseframework;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halfsummer.baseframework.result.DataGridResultInfo;
import com.halfsummer.baseframework.result.PageInfo;
import com.halfsummer.baseframework.result.ResultDataUtil;
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
        doctorVos.forEach((doctor) -> {
            System.out.println(doctor.getDepartmentName());
        });
    }

    @Test
    void selecPageDemo() {
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        Page<Doctor> doctorPage = doctorMapper.selectPage(new Page<Doctor>(1, 2), wrapper);
        System.out.println(doctorPage.getTotal() + ":" + doctorPage.getRecords());
    }

    @Test
    void selecPageCustomDemo() {
        QueryWrapper<DoctorVo> wrapper = new QueryWrapper<DoctorVo>();
        wrapper.eq("u.user_name", "张强");
        Page<DoctorVo> page = new Page<>(1, 2);
        IPage<DoctorVo> doctorVoIPage = doctorMapper.selectMyPage(page, wrapper);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(doctorVoIPage.getRecords());
        pageInfo.setTotal(doctorVoIPage.getTotal());
        DataGridResultInfo queryResult = ResultDataUtil.createQueryResult(pageInfo);
        System.out.println(queryResult);
    }
//
//    @Test
//    void insertTime(){
//        Appoint appoint = new Appoint();
//        appoint.setAppointId("1231212");
//        appoint.setUserId("12131");
//        appoint.setUserName("cesre");
//        appoint.setAppointStatus("1");
//        appoint.setAppointDate(DateUtil.formatLocalDateTime(LocalDateTime.now()));
//        appointMapper.insert(appoint);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(DateUtil.today());
//    }

}