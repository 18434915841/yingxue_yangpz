package com.baizhi.service;

import com.baizhi.entity.Admin;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface AdminService {
        HashMap<String,Object> queryByname(Admin admin, String ucode);
        void addAdmin(Admin admin);
        void deleteAdmin(String id);
        void updateAdmin(Admin admin);
        List<Admin> queryAllAdmin();


}
