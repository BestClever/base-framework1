package com.halfsummer.sys.vo;

import lombok.Data;

@Data
public class OutpatientVo {

    public String outpatientId;
    public String doctorId;
    public String doctorName;
    public String outpatientDate;
    public String outpatientNumber;
    public String appointStatus;
    public String appointDate;
    public String appointId;
    public String outpatientNotice;
    public String currentNum;
    public String patientId;//患者 id
    public String patientName;//患者姓名
    public String deptId;
    public String patientType;


}
