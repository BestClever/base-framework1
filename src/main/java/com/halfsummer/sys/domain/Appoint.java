package com.halfsummer.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 预约类实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_appoint")
@ToString
public class Appoint {
        @TableId(value = "appoint_id")
        public String appointId;
        public String userId;
        @TableField(value = "t_s_user_id",exist = true)
        public String tsUserId;
        public String username;
        public String outpatientId;
        public String appointDate;
        public String appointStage;
        public String appointStageName;
        public String appointStatus;
        public String cancelId;
        public String medicalAdvice;
        public  String nsname;
}
