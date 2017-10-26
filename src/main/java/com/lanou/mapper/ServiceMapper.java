package com.lanou.mapper;

import com.lanou.bean.Account;
import com.lanou.bean.Services;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Services record);

    // 添加
    int insertSelective(Services record);

    // 添加中的搜索idcard
    List<Account> servicelist(String idcard);

    Services selectByPrimaryKey(Integer serviceId);

    // 修改
    int updateByPrimaryKeySelective(Services services);

    int updateByPrimaryKey(Services record);

    // 显示所有
    List<Services> findallservice();

    // 开启
    void biginser(Integer id);

    // 暂停
    void pauseser(Integer id);

    // 通过id查找services
    Services findbyserid(Integer id);

    // 删除
    void deleteByserid(Integer id);

}