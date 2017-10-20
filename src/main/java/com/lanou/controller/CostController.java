package com.lanou.controller;

import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Controller
public class CostController {

    @Resource
    private CostService costService;

    @RequestMapping(value = "/fee/fee_list")
    public String find(){
        return "/fee/fee_list";
    }

    @ResponseBody
    @RequestMapping(value = "/find")
    public List<Cost> costList(){
        return costService.findAll();
    }

}
