package com.baizhi.app;


import com.baizhi.po.VideoPo;
import com.baizhi.service.VideoService;
import com.baizhi.vo.VideoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("app")
public class AppInterface {
        @Resource
    VideoService videoService;

        @GetMapping("queryByReleaseTime")
    public HashMap<String,Object> queryByReleaseTime(){
            HashMap<String, Object> map = new HashMap<>();
            try {
                List<VideoPo> videoVos = videoService.queryByReleaseTime();
                map.put("data",videoVos);
                map.put("message","查询成功");
                map.put("status",100);

            } catch (Exception e) {
                e.printStackTrace();
                map.put("data",null);
                map.put("message","查询失败");
                map.put("status",104);
            }

            return map;
    }
    @GetMapping("queryByReleaseTimes")
    public HashMap<String,Object> queryByReleaseTimes(){
        HashMap<String, Object> map = new HashMap<>();
        try {
            List<VideoPo> videoPos = videoService.queryByReleaseTimes();
            map.put("data",videoPos);
            map.put("message","查询成功");
            map.put("status",100);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("data",null);
            map.put("message","查询失败");
            map.put("status",104);
        }

        return map;
    }
}
