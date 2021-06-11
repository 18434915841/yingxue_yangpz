package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("user")
public class userController {
    
    @Resource
    UserService userService;
    @Resource
    HttpServletRequest request;


    @ResponseBody
    @RequestMapping("queryAllPage")
    public HashMap<String,Object> queryAllPage(Integer page,Integer rows){

        HashMap<String, Object> map = userService.queryAllPage(page, rows);
        return map;
    }

    @ResponseBody
    @RequestMapping("edits")
    public HashMap<String, Object> edits(User user, String oper){
        HashMap<String, Object> map = null;
        if(oper.equals("add")){
            map = userService.add(user);
        }
        if(oper.equals("del")){
            map = userService.deleteUser(user);
        }
        if(oper.equals("edit")){
            userService.updateUser(user);
        }
        return map;
    }

    @RequestMapping("updateStatus")
    public void updateStatus(User user){
        userService.updateUser(user);
    }

    @ResponseBody
    @RequestMapping("fileupload")
    public HashMap<String,Object> fileupload(MultipartFile headImg,String userId) throws IOException {


        return userService.fileUploadAliyun(headImg,userId);
    }
}
