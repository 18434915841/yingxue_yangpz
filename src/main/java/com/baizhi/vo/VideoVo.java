package com.baizhi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoVo {
    private String vid;
    private String title;
    private String description;
    private String videoPath;
    private String coverPath;
    private String cateId;
    private String groupId;
    private String userId;
    private String uploadTime;
    private String headImg;
    private String cateName;

}
