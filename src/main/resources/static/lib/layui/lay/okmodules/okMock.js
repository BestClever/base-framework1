"use strict";
layui.define([], function (exprots) {
    let okMock = {
        api: {
            doctorList:"http://localhost:8085/doctor/doctorList",
            appointAdd:"http://localhost:8085/appoint/add",
            checkToAppoint:"http://localhost:8085/doctor/checkToAppoint",
            cBook:"http://localhost:8085/doctor/cbook",
            cancel:"http://localhost:8085/appoint/aCancellation"

        }
    };
    exprots("okMock", okMock);
});
