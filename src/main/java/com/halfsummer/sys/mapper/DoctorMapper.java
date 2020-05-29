package com.halfsummer.sys.mapper;

        import com.baomidou.mybatisplus.core.mapper.BaseMapper;
        import com.halfsummer.sys.domain.Doctor;
        import com.halfsummer.sys.vo.DoctorVo;

        import java.util.List;

public interface DoctorMapper extends BaseMapper<Doctor> {
        List<Doctor> getdoctorList(DoctorVo doctorVo);
}
