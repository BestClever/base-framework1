"use strict";
layui.define([], function (exprots) {
    let okMock = {
        api: {
            doctorList:"http://localhost:8085/doctor/doctorList",
            appointAdd:"http://localhost:8085/appoint/add"
        }
    };
    exprots("okMock", okMock);
});
