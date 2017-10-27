package com.lanou.controller;

import com.lanou.bean.Module;
import com.lanou.bean.Role;
import com.lanou.service.RoleService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Controller
public class RoleController {


    @Resource
    private RoleService roleService;


    @RequestMapping(value = "/role")
    public String role(){
        return "/role/role_list";
    }

    @RequestMapping(value = "/roleadd")
    public String roleadd(){ return "/role/role_add";}

    @RequestMapping(value = "/rolemodi")
    public String rolemodi(){ return "/role/role_modi";}

    @ResponseBody
    @RequestMapping(value = "/index")
    public void index(Integer id){
        roleService.findRoleByModuleId(id);
    }

    // 显示全部
    @ResponseBody
    @RequestMapping(value = "/listrole",method = RequestMethod.POST)
    public AjaxResult listrole(){
        List<Role> roleList = roleService.findallrole();
//        System.out.println(roleList);
//        for (Role role : roleList) {
//            List<Module> moduleList = role.getModuleList();
//            for (int i = 0; i < moduleList.size(); i++) {
//                for (Module module : moduleList) {
//                    System.out.println(module.getName());
//                }
//            }
//        }
        return new AjaxResult(roleList);
    }

    // 添加
    @ResponseBody
    @RequestMapping(value = "/addrole")
    public AjaxResult addrole(){



        return new AjaxResult();
    }



}
