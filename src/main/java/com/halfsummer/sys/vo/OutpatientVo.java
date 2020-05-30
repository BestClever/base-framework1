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
    public String appointId;
    public String outpatient_notice;
}
