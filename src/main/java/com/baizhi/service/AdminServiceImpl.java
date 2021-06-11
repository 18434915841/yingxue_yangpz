package com.baizhi.service;

import com.baizhi.dao.AdminMapper;
import com.baizhi.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class  AdminServiceImpl implements AdminService{
        @Resource
    AdminMapper adminMapper;
        @Resource
    HttpSession session;


    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public HashMap<String,Object> queryByname(Admin admin, String ucode) {
        HashMap<String, Object> map = new HashMap<>();
        Admin admins = adminMapper.queryByUsername(admin.getUsername());
        String vcode = (String) session.getAttribute("vcode");
        //判断验证码是否正确
        if(vcode.equals(ucode)){
            //判断用户是否存在
            if(admins!=null){
                //判断密码是否正确
                if(admins.getPassword().equals(admin.getPassword())){
                    map.put("status","200");
                    map.put("message","登录成功");
                    session.setAttribute("admin",admins);
                }else {
                    map.put("status","400");
                    map.put("message","密码错误");
                }
            }else{
                map.put("status","400");
                map.put("message","该用户不存在");
            }
        }else {
            map.put("status","400");
            map.put("message","验证码错误");
        }
        return map;
    }

    @Override
    public void addAdmin(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        admin.setStatus("1");
        admin.setLevel("2");

        adminMapper.addAdmin(admin);
    }

    @Override
    public void deleteAdmin(String id) {
        adminMapper.deleteAdmin(id);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);
    }

    @Override
    public List<Admin> queryAllAdmin() {

        return adminMapper.queryAllAdmin();
    }
}
