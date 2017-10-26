package com.lanou.service;


import com.github.pagehelper.PageInfo;

import com.lanou.bean.Account;
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

    // 添加
    void addser(Services services);
    // 搜索idcard
    List<Account> searchidcard(String idcardNo);

    // 查看
    Services serid(Integer id);

}
