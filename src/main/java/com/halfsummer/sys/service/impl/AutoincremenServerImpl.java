package com.halfsummer.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halfsummer.sys.domain.Autoincremen;
import com.halfsummer.sys.mapper.AutoincremenMapper;
import com.halfsummer.sys.service.AutoincremenServer;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class AutoincremenServerImpl extends ServiceImpl<AutoincremenMapper, Autoincremen> implements AutoincremenServer {
    @Override
    public boolean save(Autoincremen entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Autoincremen entity) {
        return super.updateById(entity);
    }
}
