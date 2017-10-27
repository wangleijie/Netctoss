package com.lanou.mapper;

import com.lanou.bean.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleInfoMapper {
    int deleteByPrimaryKey(Integer moduleId);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer moduleId);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);


    List<Module> findModulByRoleId(@Param("role_id") Integer id);
}