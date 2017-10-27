package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
public interface CostService {

    List<Cost> findAll();

    Cost findById(Integer id);

    void addNew(Cost cost);

    void delete(Integer id);

    void begin(Integer costId);

    void updataCost(Cost cost);

    //分页
    List<Cost> findWithPageInfo(Integer pageNum,Integer pageSize);
    PageInfo<Cost> pageinfo(Integer pageSize);


    // 根据costname查
    Cost findByCostname(String costname);
}
