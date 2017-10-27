package com.lanou.mapper;

import com.lanou.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

//    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    // 显示全部
    List<Role> findallrole();

    List<Role> findRoleByModuleId(@Param("module_id") Integer id);

}