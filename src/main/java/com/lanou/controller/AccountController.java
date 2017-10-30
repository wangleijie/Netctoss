package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.service.AccountService;
import com.lanou.utils.AjaxResult;
import com.lanou.utils.IDCard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Controller
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/acc")
    public String acc() {
        return "/account/account_list";
    }

    @RequestMapping(value = "/accadd")
    public String accadd() {
        return "/account/account_add";
    }

    @RequestMapping(value = "/accsee")
    public String accsee() {
        return "/account/account_detail";
    }

    @RequestMapping(value = "/accupdate")
    public String accdel() {
        return "/account/account_modi";
    }

    // 显示全部
    @ResponseBody
    @RequestMapping(value = "/findacc", method = RequestMethod.POST)
    public AjaxResult findacc() {
        List<Account> accountList = accountService.findallacc();
//        System.out.println(accountList);
        return new AjaxResult(accountList);
    }


    //分页
    @ResponseBody
    @RequestMapping(value = "/stu1", method = RequestMethod.POST)
    public List<Account> accountList(@RequestParam("no") Integer num,
                                     @RequestParam("size") Integer size) {
        return accountService.findWithPageInfo(num, size);
    }

    @ResponseBody
    @RequestMapping(value = "/pageInfo1", method = RequestMethod.POST)
    public PageInfo<Account> pageInfo(@RequestParam("pageSize") Integer size) {
        return accountService.pageinfo(size);
    }


    // 添加
    @ResponseBody
    @RequestMapping(value = "/addacc", method = RequestMethod.POST)
    public AjaxResult addacc(Account account) throws ParseException {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String date1 = dateFormat.format(now);
        account.setCreateDate(date1);
        account.setStatus("0");


        // 通过身份证设置出生日期
        String idcardNo = account.getIdcardNo();
        String birthDate = idcardNo.substring(6, 10) + "-" + idcardNo.substring(10, 12) + "-" + idcardNo.substring(12, 14) + " 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(birthDate);
        account.setBirthdate(date);

        // 判断身份证
//        IDCard idCard = new IDCard();
//        boolean verify = idCard.Verify(account.getIdcardNo());
//        if (verify == false) {
//            return new AjaxResult(0);
//        }
//       else {
//            accountService.addacc(account);
//            return new AjaxResult(1);
//        }
        return new AjaxResult(account);
    }

    // 删除
    @ResponseBody
    @RequestMapping(value = "/delacc")
    public AjaxResult delacc(@RequestParam("delid") Integer id) {
        accountService.deleteacc(id);
        return new AjaxResult(id);
    }

    // 启用
    @ResponseBody
    @RequestMapping(value = "/beginacc", method = RequestMethod.POST)
    public AjaxResult beginacc(@RequestParam("bid") Integer id,
                               @RequestParam("status") String status) {

        accountService.beginacc(id, status);
        System.out.println(111 + status);
        return new AjaxResult(id);

    }

    // 查看
    // 把ID存session中
    @ResponseBody
    @RequestMapping(value = "/seeacc", method = RequestMethod.POST)
    public void seeid(@RequestParam("seeid") Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("seeid", id);
        System.out.println(id);
    }

    // 通过id查出来account
    @ResponseBody
    @RequestMapping(value = "/findseeacc", method = RequestMethod.POST)
    public AjaxResult findseeacc(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer seeid = (Integer) session.getAttribute("seeid");
        Account account = accountService.seebyid(seeid);
        System.out.println(account);

        return new AjaxResult(account);
    }

    // 修改
    // 1,点击修改获取前端原数据(获取id)
    @ResponseBody
    @RequestMapping(value = "/changeacc")
    public void changeacc(@RequestParam("accid") Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("accid", id);
    }

    // 2,通过id查出来account
    @ResponseBody
    @RequestMapping(value = "/findaccountbyid")
    public AjaxResult findaccountbyid(HttpSession session) {

        Integer accid = (Integer) session.getAttribute("accid");
        Account account = accountService.seebyid(accid);
        return new AjaxResult(account);
    }

    // 3,修改account
    @ResponseBody
    @RequestMapping(value = "/updateacc")
    public void updateacc(Account account) {

        accountService.updateacc(account);
    }


    // 模糊查询
    @ResponseBody
    @RequestMapping(value = "/searchacc", method = RequestMethod.POST)
    public AjaxResult searchacc(@RequestParam("idcardNo") String idcardNo,
                                @RequestParam("realname") String realname,
                                @RequestParam("logname") String logname) {

//        if (idcardNo == null || idcardNo.trim().isEmpty()) {
//            idcardNo = null;
//        }
//        if (realname == null || realname.trim().isEmpty()) {
//            realname = null;
//        }
//        if (logname == null || logname.trim().isEmpty()) {
//            logname = null;
//        }
        List<Account> accountList = accountService.searchall(idcardNo, realname, logname);

        return new AjaxResult(accountList);
    }


}
