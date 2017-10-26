package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import com.lanou.utils.AjaxResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Controller
public class CostController {

    @Resource
    private CostService costService;

    @RequestMapping(value = "/home")
    public String find() {
        return "/fee/fee_list";
    }

    @RequestMapping(value = "/feeadd")
    public String add() {
        return "fee/fee_add";
    }

    @RequestMapping(value = "/feesee")
    public String see() {
        return "fee/fee_detail";
    }

    @RequestMapping(value = "/feemodi")
    public String modi() {
        return "fee/fee_modi";
    }


    // 显示全部
    @ResponseBody
    @RequestMapping(value = "/find")
    public AjaxResult a() {
        List<Cost> costList = costService.findAll();
//        System.out.println(costList);
        return new AjaxResult(costList);
    }

    // 添加
    @ResponseBody
    @RequestMapping(value = "/add")
    public AjaxResult b(Cost cost, HttpServletRequest request) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String date = dateFormat.format(now);
        cost.setCreatime(date);
        cost.setStartime(date);
        if (cost.getBaseDuration() < 600 && cost.getBaseCost() < 100000 && cost.getUnitCost() < 100000) {
            costService.addNew(cost);
        } else {
            System.out.println("...");
        }

        return new AjaxResult(cost);
    }


    // 点击查看
    // 保存id
    @ResponseBody
    @RequestMapping(value = "/pre")
    public void b(@RequestParam("costId") Integer id, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("costId", id);
        System.out.println(id);
    }

    // 通过id查出来cost
    @ResponseBody
    @RequestMapping(value = "/findcostid")
    public AjaxResult show(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer costId = (Integer) session.getAttribute("costId");
        Cost cost = costService.findById(costId);
        System.out.println(cost);
        return new AjaxResult(cost);
    }


    // 删除
    @ResponseBody
    @RequestMapping(value = "/delete")
    public AjaxResult del(@RequestParam("delid") Integer id) {
        System.out.println(id);
        costService.delete(id);
        return new AjaxResult(id);
    }


    // 启用
    @ResponseBody
    @RequestMapping(value = "/begin")
    public void begin(@Param("costId")Integer costId) {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String date = dateFormat.format(now);
        Cost cost = new Cost();
        cost.setCostId(costId);
        cost.setStartime(date);

        costService.begin(costId);





    }


    // 点击修改
    //    修改从前端获取id
    @ResponseBody
    @RequestMapping(value = "/change")
    public void change(@Param("costId") Integer costId, HttpSession httpSession) {
        httpSession.setAttribute("changeId", costId);
    }

    //    通过id查找对应的cost
    @ResponseBody
    @RequestMapping("/findcostbyId")
    public AjaxResult findChaneCostById(HttpSession session) {
        Integer costId = (Integer) session.getAttribute("changeId");
        Cost cost = costService.findById(costId);
        return new AjaxResult(cost);
    }

    //    修改cost
    @ResponseBody
    @RequestMapping("/updateCost")
    public void updateCost(Cost cost) {
        costService.updataCost(cost);
    }



    //分页
    @ResponseBody
    @RequestMapping(value = "/stu",method = RequestMethod.POST)
    public List<Cost> costList(@RequestParam("no")Integer num,
                               @RequestParam("size")Integer size){

        return costService.findWithPageInfo(num,size);

    }

    @ResponseBody
    @RequestMapping(value = "/pageInfo",method = RequestMethod.POST)
    public PageInfo<Cost> pageInfo(@RequestParam("pageSize")Integer size){

        return costService.pageinfo(size);
    }

}
