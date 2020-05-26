package com.halfsummer.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halfsummer.sys.domain.Outpatient;
import com.halfsummer.sys.mapper.OutpatientMapper;
import com.halfsummer.sys.service.OutpatientServer;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class OutpatientServerImpl extends ServiceImpl<OutpatientMapper, Outpatient> implements OutpatientServer {
    @Override
    public boolean save(Outpatient entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Outpatient entity) {
        return super.updateById(entity);
    }
}
