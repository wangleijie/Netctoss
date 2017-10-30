package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.mapper.RoleInfoMapper;
import com.lanou.service.RoleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Resource
    private RoleInfoMapper roleInfoMapper;

    // 分页
    @Override
    public PageInfo<RoleInfo> pageinfo(Integer pageNo, Integer pageSize) {
        return queryCostByPage(pageNo, pageSize);
    }


    @Override
    public boolean findByName(String name) {
        RoleInfo role = roleInfoMapper.findByName(name);
        if (role == null) {
            return true;
        }
        return false;
    }


    // 添加
    @Override
    public void insertRole(RoleInfo roleInfo) {
        roleInfoMapper.insertSelective(roleInfo);
    }

    // 添加信息到中间表
    @Override
    public void insertIntoRoleModule(Integer roleId, Integer moduleId) {
        roleInfoMapper.insertIntoRoleModule(roleId, moduleId);
    }

    // 修改
    @Override
    public void updateRole(RoleInfo role) {
        roleInfoMapper.updateByPrimaryKey(role);
    }

    // 通过id删除中间表
    @Override
    public void deleteRoleModule(Integer roleId) {
        roleInfoMapper.deleteRoleModule(roleId);
    }

    @Override
    public RoleInfo findRoleByRoleId(Integer roleId) {
        return roleInfoMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public void delRole(Integer roleId) {
        roleInfoMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public List<RoleInfo> findAllRoles() {
        return null;
    }


    private PageInfo<RoleInfo> queryCostByPage(Integer pageNo, Integer pageSize) {

        // 判断参数的合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        // 获取全部的信息
        List<RoleInfo> roleInfoList = roleInfoMapper.findAllRoleInfo();

        // 使用PageInfo对结果进行包装
        return new PageInfo<>(roleInfoList);
    }
}
