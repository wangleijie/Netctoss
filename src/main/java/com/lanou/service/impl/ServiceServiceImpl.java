package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Services;
import com.lanou.mapper.ServiceMapper;
import com.lanou.service.ServiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@Service
public class ServiceServiceImpl implements ServiceService {

    @Resource
    private ServiceMapper serviceMapper;


    // 显示全部
    @Override
    public List<Services> findallservice() {
        return serviceMapper.findallservice();
    }


    //分页
    public List<Services> findWithPageInfo(Integer pageNum, Integer pageSize) {

        //获取PageInfo对象

        PageInfo<Services> pageInfo =queryCostByPage(pageNum,pageSize);

        return pageInfo.getList();
    }

    public PageInfo<Services> queryCostByPage(Integer pageNum, Integer pageSize){

        //判断参数的合法性
        pageNum = pageNum ==null?1: pageNum;
        pageSize = pageSize ==null?5:pageSize;

        PageHelper.startPage(pageNum,pageSize);

        //获取全部学院
        List<Services> servicesList = serviceMapper.findallservice();

        //使用PageInfo对结果进行包装
        PageInfo<Services> pageInfo = new PageInfo<Services>(servicesList);

        return pageInfo;
    }

    public PageInfo<Services> pageinfo(Integer pageSize) {
        return queryCostByPage(null,pageSize);
    }

    // 开启
    @Override
    public void beginser(Integer id, String status) {
        if (status.equals("开通")){
            serviceMapper.biginser(id);
        }
        if (status.equals("暂停")){
            serviceMapper.pauseser(id);
        }
    }

    // 添加
    @Override
    public void addser(Services services) {
        serviceMapper.insertSelective(services);
    }

    @Override
    public List<Account> searchidcard(String idcardNo) {
        return serviceMapper.servicelist(idcardNo);
    }

    // 查看
    @Override
    public Services serid(Integer id) {
        return serviceMapper.findbyserid(id);
    }

    // 删除
    @Override
    public void deletserid(Integer id) {
        serviceMapper.deleteByserid(id);
    }


    // 修改
    @Override
    public void updateser(Services services) {
        serviceMapper.updateByPrimaryKeySelective(services);
    }





}
