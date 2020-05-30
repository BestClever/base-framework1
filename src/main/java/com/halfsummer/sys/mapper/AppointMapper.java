package com.halfsummer.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halfsummer.sys.domain.Appoint;

/**
 * @author BestClever
 * @title: UserMapper
 * @projectName base-framework
 * @description: TODO
 * @date 2020-05-23 19:54
 */
public interface AppointMapper extends BaseMapper<Appoint> {


    IPage<Appoint> selectMypage(Page<Appoint> page, QueryWrapper<Appoint> wrapper);
}
