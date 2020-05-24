package com.halfsummer.sys.domain;

import lombok.Data;

/**
 * 医生实体类
 */
@Data
public class Doctor {
    //医生 id
    public String doctorId;
    //医生名字
    public String doctorName;
    //医生门诊数量
    public String outpatientNumber;
    //医生科室
    public  String department;
    //当前预约人数
    public String reservationNumber;


}
