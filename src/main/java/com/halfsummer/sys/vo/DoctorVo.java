package com.halfsummer.sys.vo;

import cn.hutool.core.clone.CloneSupport;
import lombok.Data;

@Data
public class DoctorVo extends CloneSupport<DoctorVo> {

    public String departmentId;
    //医生名字
    public String departmentName;
    //医生科室
    public  String departmentProfile;
    public  String outpatientDate;
    //医生门诊数量
    public String outpatientNumber;

    //当前预约人数
    public String reservationNumber;



}
