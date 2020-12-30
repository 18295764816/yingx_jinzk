package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;

/**
 * @author AdminController
 * @time 2020/11/12-15:40
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService as;

    //验证码
    @RequestMapping("/ImageAction")
    public String execute(HttpServletResponse response,HttpSession session) throws Exception {
        //实现验证码的功能
        CreateValidateCode cvc = new CreateValidateCode();
        String icode = cvc.getCode();
        System.out.println(icode);
        session.setAttribute("icode",icode);
        OutputStream out = response.getOutputStream();
        cvc.write(out);
        return null;
    }

    //登录
    @RequestMapping("/LoginAction")
    public String login(Admin admin,String str,HttpSession session){
        try {
            //验证码
            String icode = (String) session.getAttribute("icode");
            session.removeAttribute("icode");
            if( !icode.equals(str) ) throw new RuntimeException("验证码错误");
            as.login(admin);
            return "redirect:/main/main.jsp";
        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("message",e.getMessage());
            return "redirect:/jsp/login.jsp";
        }

    }

//    //注册
//    @RequestMapping("InsertAction")
//    public String insert(Admin admin,String str,HttpSession session){
//        try {
//            //验证码
//            String icode = (String) session.getAttribute("icode");
//            session.removeAttribute("icode");
//            if( !icode.equals(str) ) throw new RuntimeException("验证码错误");
//            as.insert(admin);
//            return "redirect:/jsp/login.jsp";
//        }catch (Exception e){
//            e.printStackTrace();
//            session.setAttribute("message",e.getMessage());
//            return "redirect:/jsp/reg.jsp";
//        }
//    }

    //安全退出
    @RequestMapping("Exit")
    public String exit( HttpSession session){
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
        return "/jsp/login.jsp";
    }
}
