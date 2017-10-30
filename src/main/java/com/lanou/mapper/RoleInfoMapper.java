package com.lanou.mapper;

import com.lanou.bean.RoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleInfoMapper {

    // 删除
    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);

   // 添加
    int insertSelective(RoleInfo record);

    // 根据id查找
    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    // 根据id修改
    int updateByPrimaryKey(RoleInfo record);

    // 查找所有财务账户信息
    List<RoleInfo> findAllRoleInfo();

    List<RoleInfo>findRoleInfoListByModuleId(@Param("moduleId") Integer moduleId);

    // 查找角色
    RoleInfo findByName(String name);

    // 添加信息到中间表
    void insertIntoRoleModule(@Param("roleId") Integer roleId, @Param("moduleId") Integer moduleId);

    // 删除
    void deleteRoleModule(Integer roleId);

    // 显示全部
    List<RoleInfo> findRolesByAdminId(Integer adminId);
}