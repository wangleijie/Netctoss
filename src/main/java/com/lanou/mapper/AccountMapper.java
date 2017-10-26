package com.lanou.mapper;

import com.lanou.bean.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    // 添加
    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    // 修改
    int updateByPrimaryKeySelective(Account account);

    int updateByPrimaryKey(Account record);

    // 显示全部
    List<Account> findallacc();

    // 删除
    void deleteaccbyid(Integer id);

    // 启用
    void beginbyid(Integer id);

    //暂停
    void beginbyid1(Integer id);

    // 查看(根据id)
    Account seebyid(Integer id);

    // 搜索
    List<Account> searchall(@Param("idcardNo") String idcardNo,@Param("realname") String realname, @Param("logname") String logname);





}