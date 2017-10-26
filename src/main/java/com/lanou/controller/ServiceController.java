package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Services;
import com.lanou.service.ServiceService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@Controller
public class ServiceController {

    @Resource
    private ServiceService serviceService;

    @RequestMapping(value = "/ser")
    public String ser(){
        return "/service/service_list";
    }

    @RequestMapping(value = "/seradd")
    public String seradd(){ return "/service/service_add";}



    // 显示所有
    @ResponseBody
    @RequestMapping(value = "/serlist",method = RequestMethod.POST)
    public AjaxResult serlist(){

        System.out.println(1111);
        List<Services> servicesList = serviceService.findallservice();
        System.out.println(servicesList);
        return new AjaxResult(servicesList);
    }

    //分页
    @ResponseBody
    @RequestMapping(value = "/stu2",method = RequestMethod.POST)
    public List<Services> costList(@RequestParam("no")Integer num,
                                   @RequestParam("size")Integer size){

        return serviceService.findWithPageInfo(num,size);
    }
    @ResponseBody
    @RequestMapping(value = "/pageInfo2",method = RequestMethod.POST)
    public PageInfo<Services> pageInfo(@RequestParam("pageSize")Integer size){

        return serviceService.pageinfo(size);
    }

    // 开启状态
    @ResponseBody
    @RequestMapping(value = "/beginser",method = RequestMethod.POST)
    public AjaxResult beginser(@RequestParam("serid") Integer id,
                               @RequestParam("status") String status){

        serviceService.beginser(id, status);
        Services services = new Services();
        System.out.println(111 + status);
        return new AjaxResult(id);
    }


    // 添加
    @ResponseBody
    @RequestMapping(value = "/addser",method = RequestMethod.POST)
    public AjaxResult addser(Services services){


        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String date1 = dateFormat.format(now);
        services.setCreateDate(date1);
        services.setStatus("0");

        serviceService.addser(services);
        return new AjaxResult(services);
    }

    //添加中查询idcard
    @ResponseBody
    @RequestMapping(value = "/searchidcard",method = RequestMethod.POST)
    public AjaxResult searchidcard(@RequestParam("idcardNo") String idcardNo){

        serviceService.searchidcard(idcardNo);

        return new AjaxResult(idcardNo);
    }










}
