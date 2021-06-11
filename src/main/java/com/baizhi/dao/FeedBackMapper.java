package com.baizhi.dao;

import com.baizhi.entity.FeedBack;

import java.util.List;

public interface FeedBackMapper {
    List<FeedBack> queryByPage(Integer page, Integer rows);
    Integer queryByCount();
    void add(FeedBack feedback);
    void delete(String id);
    void update(FeedBack feedBack);

}
