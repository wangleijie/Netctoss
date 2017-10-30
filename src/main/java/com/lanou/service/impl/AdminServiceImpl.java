package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;
import com.lanou.mapper.AdminInfoMapper;
import com.lanou.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminInfoMapper adminMapper;


    // 分页
    @Override
    public PageInfo<AdminInfo> findAdminByPageInfo(Integer moduleId, String roleName, Integer no, Integer size) {
        return getPageInfo(moduleId, roleName, no, size);
    }


    // 显示全部
    @Override
    public PageInfo<AdminInfo> queryAdminByPage(Integer pageNo, Integer pageSize) {

        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<AdminInfo> admins = adminMapper.findAllAdmins();

        PageInfo<AdminInfo> pageInfo = new PageInfo<>(admins);

        return pageInfo;
    }

    private PageInfo<AdminInfo> getPageInfo(Integer moduleId, String roleName, Integer no, Integer size) {
        no = no == null ? 1 : no;
        size = size == null ? 5 : size;
        PageHelper.startPage(no, size);
        List<AdminInfo> admins = adminMapper.fuzzyFindAdmin(moduleId, roleName);
        return new PageInfo<>(admins);
    }


    // 添加
    @Override
    public void insertAdmin(AdminInfo admin) {
        adminMapper.insertSelective(admin);
    }


    // 查找admin
    @Override
    public boolean findAdminByAdminCode(String adminCode) {
        AdminInfo admin = adminMapper.findAdminByAdminCode(adminCode);
        if (admin == null) {
            return true;
        }
        return false;
    }

    @Override
    public void insertAdminRole(Integer adminId, Integer role) {
        adminMapper.insertAdminRole(adminId, role);
    }
}
