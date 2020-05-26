package com.halfsummer.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halfsummer.sys.domain.Appoint;
import com.halfsummer.sys.mapper.AppointMapper;
import com.halfsummer.sys.service.AppointServer;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class AppointServerImpl extends ServiceImpl<AppointMapper, Appoint> implements AppointServer {
    @Override
    public boolean save(Appoint entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Appoint entity) {
        return super.updateById(entity);
    }
}
