package com.lanou.service;


import com.github.pagehelper.PageInfo;

import com.lanou.bean.Services;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface ServiceService {

    // 显示全部
    List<Services> findallservice();

    //分页
    List<Services> findWithPageInfo(Integer pageNum, Integer pageSize);
    PageInfo<Services> pageinfo(Integer pageSize);

    // 启用
    void beginser(Integer id,String status);
}
