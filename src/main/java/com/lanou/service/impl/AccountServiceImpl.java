package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.mapper.AccountMapper;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    // 显示全部
    public List<Account> findallacc() {

        return accountMapper.findallacc();
    }

    //分页
    public List<Account> findWithPageInfo(Integer pageNum, Integer pageSize) {
        //获取PageInfo对象
        PageInfo<Account> pageInfo = queryCostByPage(pageNum, pageSize);
        return pageInfo.getList();
    }

    public PageInfo<Account> queryCostByPage(Integer pageNum, Integer pageSize) {
        //判断参数的合法性
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        //获取全部学院
        List<Account> accountList = accountMapper.findallacc();
        //使用PageInfo对结果进行包装
        PageInfo<Account> pageInfo = new PageInfo<Account>(accountList);
//        System.out.println(pageInfo);
        return pageInfo;
    }

    public PageInfo<Account> pageinfo(Integer pageSize) {
        return queryCostByPage(null, pageSize);
    }


    // 添加
    public void addacc(Account account) {
        accountMapper.insertSelective(account);
    }

    // 删除
    public void deleteacc(Integer id) {
        accountMapper.deleteaccbyid(id);
    }

    // 启用
    public void beginacc(Integer id, String status) {
        if (status.equals("开通")) {
            accountMapper.beginbyid(id);
        }
        if (status.equals("暂停")) {
            accountMapper.beginbyid1(id);
        }


    }

    // 查看
    public Account seebyid(Integer id) {
        return accountMapper.seebyid(id);
    }

    // 修改
    public void updateacc(Account account) {
        accountMapper.updateByPrimaryKeySelective(account);
    }

    public List<Account> searchall(String idcardNo, String realname, String logname) {
        System.out.println(idcardNo);
        System.out.println(realname);
        System.out.println(logname);
        System.out.println(accountMapper.searchall(idcardNo, realname, logname));
        return accountMapper.searchall(idcardNo, realname, logname);
    }


}
