package com.baizhi;

import com.baizhi.dao.AdminMapper;
import com.baizhi.dao.FeedBackMapper;
import com.baizhi.dao.UserMapper;
import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.*;
import com.baizhi.service.AdminService;
import com.baizhi.service.CateService;
import com.baizhi.service.FeedBackService;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@SpringBootTest
class YingxYangpzApplicationTests {

    @Resource
    AdminMapper adminMapper;
    @Resource
    AdminService adminService;
    @Resource
    CateService cateService;
    @Resource
    VideoMapper videoMapper;


    @Test
    public void testAdmin() {
        Admin admin = adminMapper.queryByUsername("admin");
        System.out.println(admin);
    }
    @Test
    public void testAdminService(){



    }

    @Test
    void contextLoads() {
        List<Video> videos = videoMapper.selectAll();
        for (Video video : videos) {
            System.out.println(video);
        }

    }


}