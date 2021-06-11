package com.baizhi.dao;

import com.baizhi.entity.Video;

import com.baizhi.po.VideoPo;
import com.baizhi.vo.VideoVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VideoMapper extends Mapper<Video> {

    List<VideoVo> queryByReleaseTime();
    List<VideoPo> queryByReleaseTimes();
}