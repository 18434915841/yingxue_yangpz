package com.baizhi.controller;

import com.baizhi.annotation.AddLog;
import com.baizhi.entity.Category;
import com.baizhi.service.CateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
 import java.util.List;

@Controller
@RequestMapping("cate")
public class CateController {
    @Resource
    CateService cateService;

    @ResponseBody
    @RequestMapping("queryAll")
    public HashMap<String,Object> queryAll(Integer page,Integer rows){
        HashMap<String, Object> map = cateService.queryAll(page, rows);
        return map;
    }

    @ResponseBody
    @RequestMapping("queryTwoAll")
    public HashMap<String,Object> queryTwoAll(Integer page,Integer rows,String parentId){
        HashMap<String, Object> map = cateService.queryTwoAll(page, rows, parentId);
        return map;
    }

    @ResponseBody
    @RequestMapping("edits")
    public HashMap<String,Object> addCate(Category category,String oper){
        HashMap<String, Object> map= null;
        if(oper.equals("add")) {
                cateService.insertCate(category);
            }
            if(oper.equals("del")){
                map = cateService.deleteCate(category);
            }
            if(oper.equals("edit")){
                cateService.upadateCate(category);
            }
          return map;
    }

    @ResponseBody
    @RequestMapping("edits2")
    public HashMap<String,Object> addCate2(Category category, String oper){
        HashMap<String,Object> map =null;
        if(oper.equals("add")) {
             cateService.insertCate2(category);
        }
        if(oper.equals("del")){
            cateService.deleteCate2(category);
        }
        if(oper.equals("edit")){
            cateService.upadateCate2(category);
        }
        return map;
    }


}
