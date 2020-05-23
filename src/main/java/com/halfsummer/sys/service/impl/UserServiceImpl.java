package com.halfsummer.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.mapper.UserMapper;
import com.halfsummer.sys.service.UserService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author BestClever
 * @title: UserServiceimpl
 * @projectName base-framework
 * @description: TODO
 * @date 2020-05-23 19:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(User entity) {
        return super.updateById(entity);
    }
}
