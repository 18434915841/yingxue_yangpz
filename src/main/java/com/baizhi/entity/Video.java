package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "yx_video")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    private String id;
    private String title;
    private String description;
    @Column(name = "video_path")
    private String videoPath;
    @Column(name = "cover_path")
    private String coverPath;
    @Column(name = "upload_time")
    private Date uploadTime;
    @Column(name = "cate_id")
    private String cateId;
    @Column(name = "group_id")
    private String groupId;
    @Column(name = "user_id")
    private String userId;

}