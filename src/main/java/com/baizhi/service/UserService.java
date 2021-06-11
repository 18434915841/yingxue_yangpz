package com.baizhi.service;

import com.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface UserService {
    HashMap<String,Object> queryAllPage(Integer page,Integer rows);
    HashMap<String,Object> add(User user);
    HashMap<String,Object> deleteUser(User  user);
    void updateUser(User user);
    HashMap<String,Object> fileupload(MultipartFile file, String userId);
    HashMap<String,Object> fileUploadAliyun(MultipartFile file, String userId);
    void deleteBucket();
}
