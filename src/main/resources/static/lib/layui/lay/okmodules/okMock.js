"use strict";
layui.define([], function (exprots) {
    let okMock = {
        api: {
            doctorList:"http://localhost:8085/doctor/doctorList",
            appointList:"http://localhost:8085/appoint/list",
            appointAdd:"http://localhost:8085/appoint/add",
            checkToAppoint:"http://localhost:8085/doctor/checkToAppoint",
            userList:"http://localhost:8085/doctor/userList"
        }
    };
    exprots("okMock", okMock);
});
