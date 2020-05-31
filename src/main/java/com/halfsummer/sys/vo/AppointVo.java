package com.halfsummer.sys.vo;

import lombok.Data;

@Data
public class AppointVo {
    public String appointId;
    public String userId;
    public String username;
    public String outpatientId;
    public String c;
    public String appointStage;
    public String appointStageName;
    public String appointStatus;
    public String cancelId;
    public String cancelName;
    public String medicalAdvice;
    public  String nsname;
    public String patientType;//1:门诊患者2:回诊患者
    private String location;
}
