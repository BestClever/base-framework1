package com.halfsummer.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halfsummer.sys.domain.Information;
import com.halfsummer.sys.mapper.InformationMapper;
import com.halfsummer.sys.service.InformationServer;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class InformationServerImpl extends ServiceImpl<InformationMapper, Information> implements InformationServer {
    @Override
    public boolean save(Information entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Information entity) {
        return super.updateById(entity);
    }
}
