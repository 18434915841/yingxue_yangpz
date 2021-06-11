package com.baizhi.service;

import com.baizhi.entity.Category;

import java.util.HashMap;
import java.util.List;

public interface CateService {
    HashMap<String,Object> queryAll(Integer page,Integer rows);
    void insertCate(Category category);
    HashMap<String,Object> deleteCate(Category category);
    void upadateCate(Category category);
    HashMap<String,Object> queryTwoAll(Integer page,Integer rows,String parentId);
    void insertCate2(Category category);
    void deleteCate2(Category category);
    void upadateCate2(Category category);

}
