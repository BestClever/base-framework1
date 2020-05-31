package com.halfsummer.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halfsummer.sys.mapper.DeprecatedMapper;
import com.halfsummer.sys.service.DepartmentServer;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServerImpl extends ServiceImpl<DeprecatedMapper, Deprecated> implements DepartmentServer {
}
