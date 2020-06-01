"use strict";
layui.define([], function (exprots) {
    let okMock = {
        api: {
            doctorList:okadim.baseUrl+"doctor/doctorList",
            appointList:okadim.baseUrl+"appoint/list",
            appointAdd:okadim.baseUrl+"appoint/add",
            checkToAppoint:okadim.baseUrl+"doctor/checkToAppoint",
            userList:okadim.baseUrl+"doctor/userList"
        }
    };
    exprots("okMock", okMock);
});
