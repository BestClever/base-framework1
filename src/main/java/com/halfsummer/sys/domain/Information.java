package com.halfsummer.sys.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_information")
@ToString
/**
 * 医院信息实体
 */
public class Information {
    @TableId(value = "id")
    public String id;
    public String introduction;
    public String announcement;
}
