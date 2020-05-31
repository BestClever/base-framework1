package com.halfsummer.sys.vo;

import lombok.Data;

/**
 * @author BestClever
 * @title: UserVo
 * @projectName base-framework
 * @description: TODO
 * @date 2020-05-23 20:35
 */
@Data
public class UserVo {

    private String userId;
    private String userName;
    private String password;
    private String userSex;
    private String birthDay;
    private String phone;
    private String roleCode;//1:患者2:医生3:管理员
    private String roleName;
    private String departmentId;
}
