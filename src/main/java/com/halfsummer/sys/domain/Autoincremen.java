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
@TableName("t_sys_autoincrement")
@ToString
public class Autoincremen {
    @TableId(value = "seq_name")
    public String seqName;
    public String currentVal;
    public String incrementVal;
}
