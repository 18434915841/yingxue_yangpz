package com.baizhi.controller;


import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("video")
public class VideoController {

    @Resource
    VideoService videoService;


    @RequestMapping("showAll")
    public HashMap<String,Object> showAll(Integer page,Integer rows){
        HashMap<String, Object> map = videoService.queryAll(page,rows);

        return map;
    }


    @RequestMapping("edits")
    public HashMap<String,Object> edits(Video video,String oper){
            if(oper.equals("add")){
                HashMap<String, Object> map = videoService.insert(video);
                return map;
            }
            if(oper.equals(("del"))){
                HashMap<String, Object> map = videoService.delete(video);
                return map;
            }
            if(oper.equals("edit")){
                videoService.update(video);
            }
            return null;
    }



    @RequestMapping("fileupload")
    public HashMap<String,Object> fileupload(MultipartFile file,String videoId){
        HashMap<String, Object> map = videoService.fileUploadAliyun(file,videoId);
        return map;
    }


}
