package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.service.RoleInfoService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RoleInfoController {

    @Resource
    private RoleInfoService roleInfoService;

    @RequestMapping(value = "/role/role_list")
    public String account() {
        return "/role/role_list";
    }

    @RequestMapping(value = "/role/role_add")
    public String add() {
        return "/role/role_add";
    }

    @RequestMapping(value = "/role/role_modi")
    public String accountModi() {
        return "/role/role_modi";
    }




    // 显示全部
    @ResponseBody
    @RequestMapping(value = "/roleinfopage")
    public PageInfo<RoleInfo> roleInfoPage(@RequestParam("no") Integer pageNo,
                                           @RequestParam("size") Integer pageSize) {
        return roleInfoService.pageinfo(pageNo, pageSize);
    }

    // 添加
    @ResponseBody
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public AjaxResult insertRole(@RequestParam("name") String name,
                                 @RequestParam("modules") String mle) {
        if (mle.equals("") || mle == null) {
            return new AjaxResult(0);
        }
        String[] str = mle.split(",");
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setName(name);
        roleInfoService.insertRole(roleInfo);
        for (String s : str) {
            roleInfoService.insertIntoRoleModule(roleInfo.getRoleId(), Integer.parseInt(s));
        }
        return new AjaxResult(2);
    }

    // 修改
    // 通过id查出来,存到session中
    @ResponseBody
    @RequestMapping(value = "/modirole")
    public AjaxResult modiRole(@RequestParam("roleId") Integer roleId,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();

        RoleInfo role = roleInfoService.findRoleByRoleId(roleId);
        System.out.println(role);
        session.setAttribute("role", role);
        return new AjaxResult(role);
    }

   // 修改页面上信息
    @ResponseBody
    @RequestMapping(value = "/getRoleDetail")
    public AjaxResult getRoleDetail(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RoleInfo role = (RoleInfo) session.getAttribute("role");
        return new AjaxResult(role);
    }

    //保存修改后的角色信息
    @ResponseBody
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public AjaxResult updateRole(@RequestParam("roleId") Integer roleId,
                                 @RequestParam("name") String name,
                                 @RequestParam("modules") String mle) {

        if (mle.equals("") || mle == null) {
            return new AjaxResult(0);
        }
        String[] str = mle.split(",");
        RoleInfo role = new RoleInfo();
        role.setRoleId(roleId);
        role.setName(name);

        //修改角色表
        roleInfoService.updateRole(role);

        //根据角色删除中间数据
        roleInfoService.deleteRoleModule(roleId);

        //重新插入中间表
        for (String s : str) {
            roleInfoService.insertIntoRoleModule(roleId, Integer.parseInt(s));
        }
        return new AjaxResult(2);
    }

   // 删除
    @ResponseBody
    @RequestMapping(value = "/delRole")
    public AjaxResult delRole(@RequestParam("roleId") Integer roleId) {
        roleInfoService.deleteRoleModule(roleId);
        roleInfoService.delRole(roleId);
        return new AjaxResult(roleId);
    }

    // 显示全部
    @ResponseBody
    @RequestMapping(value = "/getallRoles")
    public AjaxResult getallRoles() {
        List<RoleInfo> roles = roleInfoService.findAllRoles();
        return new AjaxResult(roles);
    }
}
