package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
public interface AccountService {

    // 显示全部
    List<Account> findallacc();

    //分页
    List<Account> findWithPageInfo(Integer pageNum, Integer pageSize);
    PageInfo<Account> pageinfo(Integer pageSize);

    // 删除
    void deleteacc(Integer id);

    // 启用
    void beginacc(Integer id,String status);

    // 查看
    Account seebyid(Integer id);

    // 修改
    void updateacc(Account account);

    // 搜索
    List<Account> searchall(String idcardNo,String realname,String logname);

    // 添加
    void addacc(Account account);

    // 根据idcard查
    Account findByIdcard(String idcardNo);



}
