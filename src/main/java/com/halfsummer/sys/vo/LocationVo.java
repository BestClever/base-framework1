package com.halfsummer.sys.vo;

import lombok.Data;

/**
 * 排队返回类
 */
@Data
public class LocationVo {
    //前方人数
    public String peopleNum;
    //等待时间
    public String WaitingTime;
    //开关:1 为当前待诊断患者 否则则是排队状态
    public  String swiTch;


}
