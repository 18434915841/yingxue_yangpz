package com.baizhi.controller;


import com.baizhi.entity.FeedBack;
import com.baizhi.service.FeedBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("feedback")
public class FeedBackController {
    //private static final Logger log = LoggerFactory.getLogger(FeedBack.class);
    @Resource
    FeedBackService feedBackService;


    @ResponseBody
    @RequestMapping("queryByPage")
    public HashMap<String,Object> queryByPage(Integer page,Integer rows){
        //log.info("page:{}",page);
        //log.info("rows:{}",rows);
        HashMap<String, Object> map = feedBackService.queryByPage(page, rows);

        return  map;
    }

    @ResponseBody
    @RequestMapping("edits")
    public Object add(FeedBack feedBack,String oper){
            if(oper.equals("add")){
                feedBackService.add(feedBack);
            }
            if(oper.equals("del")){
                feedBackService.delete(feedBack.getId());
            }
            if(oper.equals("edit")){
                feedBackService.update(feedBack);
            }
        return null;
    }
}
