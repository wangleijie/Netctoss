package com.lanou.service.impl;

import com.lanou.bean.Role;
import com.lanou.mapper.RoleInfoMapper;
import com.lanou.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleInfoMapper roleInfoMapper;


    @Override
    public void findRoleByModuleId(Integer id) {
        roleInfoMapper.findRoleByModuleId(id);
    }

    @Override
    public List<Role> findallrole() {
        return roleInfoMapper.findallrole();
    }
}
