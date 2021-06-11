package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPo;
import com.baizhi.vo.VideoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface VideoService {
    HashMap<String,Object> queryAll(Integer page,Integer rows);
    HashMap<String,Object> insert(Video video);
    HashMap<String,Object> delete(Video video);
    void update(Video video);
    HashMap<String,Object> fileUploadAliyun(MultipartFile file,String videoId);
    List<VideoPo> queryByReleaseTime();
    List<VideoPo> queryByReleaseTimes();
}
