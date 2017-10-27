package com.lanou.service.impl;

import com.lanou.mapper.ModuleInfoMapper;
import com.lanou.service.ModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/27.
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleInfoMapper moduleInfoMapper;


    @Override
    public void findModuleByRoleId(Integer id) {
        moduleInfoMapper.findModulByRoleId(id);
    }
}
