package com.lanou.service;

import com.lanou.bean.Role;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public interface RoleService {
    void findRoleByModuleId(Integer id);

    List<Role> findallrole();

}
