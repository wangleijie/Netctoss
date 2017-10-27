package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;

    public List<Cost> findAll() {
        return costMapper.findAllCost();
    }

    public Cost findById(Integer id) {
        return costMapper.findById(id);
    }

    public void addNew(Cost cost) {
        costMapper.insertSelective(cost);
    }

    public void delete(Integer id) {
        costMapper.deletById(id);
    }
//启用
    public void begin(Integer costId) {
        costMapper.updateByPrimaryKey(costId);
    }






    public void updataCost(Cost cost) {
         costMapper.updateByPrimaryKeySelective(cost);
    }





    //分页
    public List<Cost> findWithPageInfo(Integer pageNum, Integer pageSize) {

        //获取PageInfo对象

        PageInfo<Cost> pageInfo =queryCostByPage(pageNum,pageSize);


        return pageInfo.getList();



    }

    public PageInfo<Cost> queryCostByPage(Integer pageNum,Integer pageSize){

        //判断参数的合法性
        pageNum = pageNum ==null?1: pageNum;
        pageSize = pageSize ==null?5:pageSize;

        PageHelper.startPage(pageNum,pageSize);

        //获取全部学院
        List<Cost> costList = costMapper.findAllCost();

        //使用PageInfo对结果进行包装
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);

//        System.out.println(pageInfo);

        return pageInfo;

    }

    public PageInfo<Cost> pageinfo(Integer pageSize) {
        return queryCostByPage(null,pageSize);
    }




    @Override
    public Cost findByCostname(String costname) {
        return costMapper.findByCostname(costname);
    }

}
