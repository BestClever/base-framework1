package com.halfsummer.sys.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 门诊实体类
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_outpatient")
@ToString
public class Outpatient {
    @TableId(value = "outpatient_id")
    public String outpatientId;
    public String doctorId;
    public String doctorName;
    public String outpatientDate;
    public String outpatientNumber;
    public String outpatientNotice;
    public String currentNum;
    public String deptId;
}
