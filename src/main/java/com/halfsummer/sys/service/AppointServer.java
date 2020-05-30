package com.halfsummer.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halfsummer.baseframework.result.PageWrapper;
import com.halfsummer.sys.domain.Appoint;


public interface AppointServer extends IService<Appoint> {
    PageWrapper listAllInfo();
}
