package com.lanou.controller;

import com.lanou.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/27.
 */
@Controller
public class ModuleController {

    @Resource
    private ModuleService moduleService;

    @RequestMapping(value = "/index1")
    public String index1(){
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/index2")
    public void index2(Integer id){
        moduleService.findModuleByRoleId(id);
    }


}
