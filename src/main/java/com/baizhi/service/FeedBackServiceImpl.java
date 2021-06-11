package com.baizhi.service;

import com.baizhi.annotation.AddLog;
import com.baizhi.dao.FeedBackMapper;
import com.baizhi.entity.FeedBack;
import com.baizhi.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FeedBackServiceImpl implements FeedBackService{

    @Resource
    FeedBackMapper feedBackMapper;

    @Override                                  //当前页数      每页显示的行数
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //设置当前页数
        map.put("page",page);
        //查询总条数
        Integer count = feedBackMapper.queryByCount();
        map.put("count",count);
        //设置总页数
        Integer total=count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        List<FeedBack> feedBacks = feedBackMapper.queryByPage((page - 1)*rows, rows);
        map.put("rows",feedBacks);


        return map;
    }


    @AddLog(value = "添加反馈信息")
    @Override
    public void add(FeedBack feedBack) {
        feedBack.setId(UUID.randomUUID().toString());
        feedBack.setUserId(feedBack.getUserId());
        feedBack.setFeedTime(new Date());
        feedBackMapper.add(feedBack);
    }

    @AddLog(value = "删除反馈信息")
    @Override
    public void delete(String id) {
        feedBackMapper.delete(id);
    }

    @AddLog(value = "修改反馈信息")
    @Override
    public void update(FeedBack feedBack) {
        feedBackMapper.update(feedBack);
    }
}
