package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.RoleInfo;

import java.util.List;

public interface RoleInfoService {

    // 分页
    PageInfo<RoleInfo> pageinfo(Integer pageNo, Integer pageSize);

    // 查找角色
    boolean findByName(String name);

    // 添加
    void insertRole(RoleInfo roleInfo);

    //添加到中间表
    void insertIntoRoleModule(Integer roleId, Integer moduleId);

    // 修改
    void updateRole(RoleInfo role);

    //根据id删除中间表的内容
    void deleteRoleModule(Integer roleId);

    // 通过id查找角色
    RoleInfo findRoleByRoleId(Integer roleId);

    // 删除
    void delRole(Integer roleId);

    // 显示全部
    List<RoleInfo> findAllRoles();

}
