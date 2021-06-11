package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("admin")
public class AdminController {


    @Autowired
    AdminService adminService;

    @ResponseBody
    @RequestMapping("login")
    public HashMap<String, Object> login(Admin admin, String ucode,HttpSession session){
        HashMap<String, Object> map = adminService.queryByname(admin, ucode);
        return map;
    }


    @RequestMapping("getImageCode")
    @ResponseBody
    public void getImage( HttpSession session, HttpServletResponse response) throws IOException {
        //获得随机字符
        String securityCode = ImageCodeUtil.getSecurityCode();
        session.setAttribute("vcode",securityCode);
        //打印随机字符
        System.out.println("===="+securityCode);
        //生成图片
        BufferedImage image = ImageCodeUtil.createImage(securityCode);
        //图片输出
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "png", sos);

    }

    @RequestMapping("exit")
    public String exit(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }



    @RequestMapping("edits")
    public void adits(Admin admin,String oper){
        if(oper.equals("add")){
            adminService.addAdmin(admin);
        }
        if(oper.equals("del")){
            adminService.deleteAdmin(admin.getId());
        }
        if(oper.equals("edit")){
            adminService.updateAdmin(admin);
        }

    }



    @RequestMapping("queryAllAdmin")
    @ResponseBody
    public List<Admin> queryAllAdmin(){
        List<Admin> list = adminService.queryAllAdmin();
        System.out.println(list);
        return list;
    }



}
