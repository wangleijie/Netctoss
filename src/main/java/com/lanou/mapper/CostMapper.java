package com.lanou.mapper;

import com.lanou.bean.Cost;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(Integer costId);

    // 添加
    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer costId);

    // 修改
    int updateByPrimaryKeySelective(Cost cost);

    int updateByPrimaryKey(Integer costId);

    // 显示全部
    List<Cost> findAllCost();


    // 查看(根据id查)
    Cost findById(Integer id);


    // 删除(根据id)
    void deletById(Integer id);

    // 启用
    String updaestatus(Integer costId);


    //
    Cost findByCostname(String costname);


}