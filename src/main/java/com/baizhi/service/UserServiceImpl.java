package com.baizhi.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baizhi.annotation.AddLog;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.util.AliyunOSSUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Resource
    UserMapper userMapper;
    @Resource
    HttpServletRequest request;

    @Override                                   //当前页       每页展示几条数据
    public HashMap<String, Object> queryAllPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        //创建查询条件对象
        UserExample example = new UserExample();

        //查询数据总条数
        int records = userMapper.selectCountByExample(example);
        map.put("records",records);
        //计算总页数
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("total",total);
        List<User> users = userMapper.selectByExampleAndRowBounds(example, new RowBounds((page - 1) * rows, rows));
        map.put("rows",users);

        return map;
    }
    @AddLog(value = "添加用户操作")
    @Override
    public HashMap<String,Object> add(User user) {
        HashMap<String, Object> map = new HashMap<>();
        user.setId(UUID.randomUUID().toString());
        user.setRegistTime(new Date());
        user.setStatus("1");
        user.setWechat(user.getPhone());

        userMapper.insert(user);
        map.put("userId",user.getId());

        return map;
    }
    @AddLog(value = "删除用户操作")
    @Override
    public HashMap<String,Object> deleteUser(User user) {
        HashMap<String, Object> map = new HashMap<>();
        User users = userMapper.selectOne(user);
        userMapper.deleteUser(users.getId());
        String headImg = users.getHeadImg();
        String realPath = request.getSession().getServletContext().getRealPath("/upload/headImg");
        //根据文件名和绝对路径获取到这个文件
        File file = new File(realPath, headImg);
        if(file.exists()&& file.isFile()){
            boolean delete = file.delete();
            if(delete) {map.put("message","删除成功");
            }else {
                map.put("message","删除失败");
            }
        }
        return map;
    }

    @AddLog(value = "修改用户操作")
    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public HashMap<String, Object> fileupload(MultipartFile headImg, String userId) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            //获取文件名
            String filename = headImg.getOriginalFilename();
            //给文件名拼上时间戳
            String newName = new Date().getTime() + "-" + filename;
            //根据文件相对路径获取绝对路径
            String realPath = request.getSession().getServletContext().getRealPath("/upload/headImg");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            headImg.transferTo(new File(realPath, newName));
            map.put("message", "上传成功");

            User user = new User();
            user.setId(userId);
            user.setHeadImg(newName);
            userMapper.updateByPrimaryKeySelective(user);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("message", "上传失败");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> fileUploadAliyun(MultipartFile headImg, String userId) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            String filename = headImg.getOriginalFilename();
            //文件名拼接时间戳
            String newName= new Date().getTime()+"-"+filename;
            //文件名拼接文件夹名
            String objectName= "headImg/"+newName;
            //存储空间名称
            String bucketName="yingx--2010";
            //文件上传阿里云
            AliyunOSSUtil.uploadByteFileAliyun(bucketName,objectName,headImg);
            //修改文件信息
            User user = new User();
            user.setId(userId);
            user.setHeadImg("http://yingx--2010.oss-cn-beijing.aliyuncs.com/"+objectName);
            userMapper.updateByPrimaryKeySelective(user);
            map.put("message","上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","上传失败");
        }
        return map;
    }
    public void deleteBucket(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://yingx--2010.oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId ="LTAI5tScYQxVjSLwprWbnauA";
        String accessKeySecret ="IxaCND2Ds6c35abLoOhyPeI60hfhai";

        String bucketName = "2010test";  //指定要创建的存储空间名

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除存储空间。
        ossClient.deleteBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
