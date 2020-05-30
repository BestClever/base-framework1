package com.halfsummer.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halfsummer.baseframework.result.PageWrapper;
import com.halfsummer.sys.domain.Appoint;
import com.halfsummer.sys.domain.Doctor;
import com.halfsummer.sys.mapper.AppointMapper;
import com.halfsummer.sys.service.AppointServer;
import com.halfsummer.sys.vo.AppointVo;
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

    @Override
    public PageWrapper listAllInfo() {
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
//        Page<Doctor> doctorPage = doctorMapper.selectPage(new Page<Doctor>(1, 2), wrapper);
        return  null;
    }
}
