package com.halfsummer.sys.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

//import java.sql.Date;

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
        public String userName;
        public String outpatientId;
        public String appointDate;
        public String appointStage;
        public String appointStageName;
        public String appointStatus;
        public String cancelId;
        public String cancelName;
        public String medicalAdvice;
        public String patientType;
        private String location;
}
