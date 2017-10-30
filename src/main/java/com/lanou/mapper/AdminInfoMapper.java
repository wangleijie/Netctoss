package com.lanou.mapper;

import com.lanou.bean.AdminInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);

    // 根据id查找
    List<AdminInfo> fuzzyFindAdmin(@Param("moduleId") Integer moduleId, @Param("roleName") String roleName);

    List<AdminInfo> findAllAdmins();

    AdminInfo findAdminByAdminCode(String adminCode);


    void insertAdminRole(Integer adminId, Integer role);
}