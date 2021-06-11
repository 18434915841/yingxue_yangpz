package com.baizhi.service;

import com.baizhi.entity.FeedBack;

import java.util.HashMap;

public interface FeedBackService {
    HashMap<String,Object>  queryByPage(Integer page,Integer rows);

    void add(FeedBack feedBack);
    void delete(String id);
    void update(FeedBack feedBack);
}
