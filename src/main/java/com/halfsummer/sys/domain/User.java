package com.halfsummer.sys.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author BestClever
 * @title: User
 * @projectName base-framework
 * @description: TODO
 * @date 2020-05-23 20:35
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user")
@ToString
public class User {
    @TableId(value = "user_id")
    private String userId;
    private String userName;
    private String password;
    private String userSex;
    private String birthDay;
    private String phone;
    private String roleCode;
    private String roleName;


}
