package com.halfsummer.sys.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 医生实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_department")
@ToString
public class Doctor {
    //医生 id
    @TableId(value = "department_Id")
    public String departmentId;
    //医生名字
    public String departmentName;
    //医生科室
    public  String departmentProfile;



}
