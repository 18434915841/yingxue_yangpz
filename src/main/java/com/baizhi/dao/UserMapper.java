package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    User queryAllPage(Integer page,Integer rows);
    Integer queryByCount();
    void addUser(User user);
    void deleteUser(String id);
    void updateUser(User user);
}