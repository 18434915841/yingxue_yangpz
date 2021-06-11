package com.baizhi.service;

import com.baizhi.annotation.AddLog;
import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.Video;
import com.baizhi.entity.VideoExample;
import com.baizhi.po.VideoPo;
import com.baizhi.util.AliyunOSSUtil;
import com.baizhi.vo.VideoVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class VideoServiceImpl implements VideoService{
    @Resource
    VideoMapper videoMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> queryAll(Integer page,Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        Video video = new Video();
        //查询总条数
        int count = videoMapper.selectCount(video);
        map.put("count",count);
        //查询总页数
        int total=count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        //相当于是一个条件，没有条件对所有数据进行分页
        VideoExample example= new VideoExample();
        //分页查询： 参数：忽略几条,获取几条数据
        RowBounds rowBounds = new RowBounds(0,rows);
        List<Video> list = videoMapper.selectByExampleAndRowBounds(example, rowBounds);
        map.put("rows",list);
        return map;
    }

    @AddLog(value = "添加视频操作")
    @Override
    public HashMap<String, Object> insert(Video video) {
        HashMap<String, Object> map = new HashMap<>();
        video.setId(UUID.randomUUID().toString());
        video.setUploadTime(new Date());
//        video.setUserId(video.getUserId());
//        video.setVideoPath(video.getVideoPath());
        videoMapper.insert(video);
         map.put("videoId",video.getId());
        return map;
    }
    @AddLog(value = "删除视频操作")
    @Override
    public HashMap<String, Object> delete(Video video) {
        HashMap<String, Object> map = new HashMap<>();
        VideoExample example = new VideoExample();
        example.createCriteria().andIdEqualTo(video.getId());
        videoMapper.deleteByExample(example);
    return map;
    }
    @AddLog(value = "修改视频操作")
    @Override
    public void update(Video video) {
        videoMapper.updateByPrimaryKeySelective(video);

    }

    @Override
    public HashMap<String, Object> fileUploadAliyun(MultipartFile file, String videoId) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            //获取文件名
            String filename = file.getOriginalFilename();
            //文件名拼接时间戳
            String newName= new Date().getTime()+"-"+filename;
            //文件名拼接文件夹
            String objectName ="video/"+newName;
            String bucketName ="yingx--2010";
            /**
             *将文件以转为字节数组上传至阿里云
             *  参数:
             *  bucketName(String):指定存储空间名
             *  fileName(String):文件名
             *  headImg(MultipartFile):MultipartFile类型的文件
             * */
            //文件上传到阿里云
            AliyunOSSUtil.uploadByteFileAliyun(bucketName,objectName,file);
            //修改图片信息
            Video video = new Video();
            video.setId(video.getId());
            video.setVideoPath("http://yingx--2010.oss-cn-beijing.aliyuncs.com/"+objectName);
            videoMapper.updateByPrimaryKeySelective(video);
            map.put("message","上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","上传失败");
        }
        return map;
    }

    @Override
    public List<VideoPo> queryByReleaseTime() {
        ArrayList<VideoPo> videoPOS = new ArrayList<>();
        List<VideoVo> videoVos = videoMapper.queryByReleaseTime();
        for (VideoVo videoVo : videoVos) {
            VideoPo videoPo = new VideoPo();
            videoPo.setId(videoVo.getVid());
            videoPo.setCover(videoVo.getCoverPath());
            videoPo.setVideoTitle(videoVo.getTitle());
            videoPo.setPath(videoVo.getVideoPath());
            videoPo.setUploadTime(videoVo.getUploadTime());
            videoPo.setDescription(videoVo.getDescription());
            videoPo.setUserPhoto(videoVo.getHeadImg());
            videoPo.setCateName(videoVo.getCateName());
            videoPOS.add(videoPo);
        }
        return videoPOS;
    }

    @Override
    public List<VideoPo> queryByReleaseTimes() {
        ArrayList<VideoPo> videoPOS = new ArrayList<>();
        List<VideoPo> videoPos = videoMapper.queryByReleaseTimes();
        for (VideoPo videoPo : videoPos) {
            videoPOS.add(videoPo);
            System.out.println(videoPo);
        }

        return videoPOS;
    }
}
