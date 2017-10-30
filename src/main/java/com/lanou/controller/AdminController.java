package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;
import com.lanou.service.AdminService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class AdminController {

    @Resource
    private AdminService adminService;


    @RequestMapping(value = "/admin/admin_list")
    public String account() {
        return "/admin/admin_list";
    }

    @RequestMapping(value = "/admin/admin_add")
    public String add() {
        return "/admin/admin_add";
    }


    // 分页显示全部
    @ResponseBody
    @RequestMapping(value = "/getAllAdmins")
    public PageInfo<AdminInfo> getAllAdmin(@RequestParam("no") Integer pageNo,
                                           @RequestParam("size") Integer pageSize) {
        return adminService.queryAdminByPage(pageNo, pageSize);
    }

    //保存信息
    @ResponseBody
    @RequestMapping(value = "/addAdmin")
    public AjaxResult addAdmin(@RequestParam("name") String name,
                               @RequestParam("adminCode") String adminCode,
                               @RequestParam("password") String password,
                               @RequestParam("telephone") String telephone,
                               @RequestParam("email") String email,
                               @RequestParam("roles") Integer[] roles) {

        if (roles.length == 0 || roles == null) {
            return new AjaxResult(0);
        }
        if (!adminService.findAdminByAdminCode(adminCode)) {
            return new AjaxResult(1);
        }
        AdminInfo admin = new AdminInfo();

        admin.setName(name);
        admin.setAdminCode(adminCode);
        admin.setEnrolldate(new Date());
        admin.setPassword(password);
        admin.setEmail(email);
        admin.setTelephone(telephone);

        adminService.insertAdmin(admin);

        for (Integer role : roles) {
            adminService.insertAdminRole(admin.getAdminId(), role);
        }
        return new AjaxResult(2);
    }
}
