package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;


public interface AdminService {

    // 分页
    PageInfo<AdminInfo> findAdminByPageInfo(Integer moduleId, String roleName, Integer no, Integer size);

    // 显示全部
    PageInfo<AdminInfo> queryAdminByPage(Integer pageNo, Integer pageSize);

    // 添加
    void insertAdmin(AdminInfo admin);

    //查找admin
    boolean findAdminByAdminCode(String adminCode);

    // 向中间表插数据
    void insertAdminRole(Integer adminId, Integer role);
}
