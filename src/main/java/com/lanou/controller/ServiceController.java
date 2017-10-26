package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.bean.Services;
import com.lanou.service.AccountService;
import com.lanou.service.CostService;
import com.lanou.service.ServiceService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Resource
    private AccountService accountService;
    @Resource
    private CostService costService;

    @RequestMapping(value = "/ser")
    public String ser() {
        return "/service/service_list";
    }

    @RequestMapping(value = "/seradd")
    public String seradd() {
        return "/service/service_add";
    }

    @RequestMapping(value = "/sersee")
    public String sersee() {
        return "/service/service_detail";
    }

    @RequestMapping(value = "/sermodi")
    public String serdetail(){ return "/service/service_modi";}


    // 显示所有
    @ResponseBody
    @RequestMapping(value = "/serlist", method = RequestMethod.POST)
    public AjaxResult serlist() {

        System.out.println(1111);
        List<Services> servicesList = serviceService.findallservice();
        System.out.println(servicesList);
        return new AjaxResult(servicesList);
    }

    //分页
    @ResponseBody
    @RequestMapping(value = "/stu2", method = RequestMethod.POST)
    public List<Services> costList(@RequestParam("no") Integer num,
                                   @RequestParam("size") Integer size) {

        return serviceService.findWithPageInfo(num, size);
    }

    @ResponseBody
    @RequestMapping(value = "/pageInfo2", method = RequestMethod.POST)
    public PageInfo<Services> pageInfo(@RequestParam("pageSize") Integer size) {

        return serviceService.pageinfo(size);
    }

    // 开启状态
    @ResponseBody
    @RequestMapping(value = "/beginser", method = RequestMethod.POST)
    public AjaxResult beginser(@RequestParam("serid") Integer id,
                               @RequestParam("status") String status) {

        serviceService.beginser(id, status);
        Services services = new Services();
        System.out.println(111 + status);
        return new AjaxResult(id);
    }


    // 添加
    @ResponseBody
    @RequestMapping(value = "/addser", method = {RequestMethod.POST, RequestMethod.GET})
    public AjaxResult addser(Services services) {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String date1 = dateFormat.format(now);
        services.setCreateDate(date1);
        services.setStatus("0");
//        services.setServiceId(1234);
//        services.setCostId(8);
        System.out.println(services);

        serviceService.addser(services);

        return new AjaxResult(services);
    }

    //添加中通过idcard查找账务账号
    @ResponseBody
    @RequestMapping(value = "/searchidcard", method = RequestMethod.POST)
    public AjaxResult searchidcard(@RequestParam("idcardNo") String idcardNo) {
//        serviceService.searchidcard(idcardNo);
        // 在account中通过idcard查accountId
        Account account = accountService.findByIdcard(idcardNo);
//        System.out.println(account);
        // 并且返回accountId
        return new AjaxResult(account.getAccountId());
    }


    //  第二步:把Cost中的值存到session中,并返回
    @ResponseBody
    @RequestMapping(value = "/findCost",method = RequestMethod.POST)
    public String findCost(HttpServletRequest request) {
        List<Cost> costs = costService.findAll();
//        System.out.println(costs);
        request.getSession().setAttribute("findCostList", costs);
        return "";
    }

    // 第四步: 取出session中的Cost值, 并返回
    @ResponseBody
    @RequestMapping(value = "/findCostListS",method = RequestMethod.POST)
    public List<Cost> findCostListS(HttpServletRequest request) {

        List<Cost> costs = (List<Cost>) request.getSession().getAttribute("findCostList");
        for (Cost cost : costs) {
            System.out.println(cost.getName());
        }
        return costs;

//        return (List<Cost>) request.getSession().getAttribute("findCostList");
    }


    // 点击查看
    // 存session中
    @ResponseBody
    @RequestMapping(value = "/seeser", method = RequestMethod.POST)
    public void seeser(@RequestParam("sid") Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("sid", id);
        System.out.println(id);
    }

    // 通过id查出来
    @ResponseBody
    @RequestMapping(value = "/findbyserid", method = RequestMethod.POST)
    public AjaxResult findbyserid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer serid = (Integer) session.getAttribute("sid");
        Services services = serviceService.serid(serid);
//        System.out.println(services);
        return new AjaxResult(services);
    }

    // 删除
    @ResponseBody
    @RequestMapping(value = "/delser")
    public AjaxResult delser(@RequestParam("delserid") Integer id){
        serviceService.deletserid(id);
        return new AjaxResult(id);
    }

    // 修改
    // 点击根据id获取
    @ResponseBody
    @RequestMapping(value = "/changeser", method = RequestMethod.POST)
    public void changeser(@RequestParam("serid") Integer id,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("serid",id);
    }

    // 通过id查出来Service
    @ResponseBody
    @RequestMapping(value = "/findbySerid", method = RequestMethod.POST)
    public AjaxResult findbySerid(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer serid = (Integer) session.getAttribute("serid");
        Services services = serviceService.serid(serid);
        return new AjaxResult(services);
    }

    // 修改Service
    @ResponseBody
    @RequestMapping(value = "/updateser", method = RequestMethod.POST)
    public void updateser(Services services){
        serviceService.updateser(services);
    }



}
