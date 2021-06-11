package com.baizhi.dao;

import com.baizhi.entity.Admin;

import java.util.List;

public interface AdminMapper {
    Admin queryByUsername(String username);
    void addAdmin(Admin admin);
    void deleteAdmin(String id);
    void updateAdmin(Admin admin);
    List<Admin> queryAllAdmin();
}
