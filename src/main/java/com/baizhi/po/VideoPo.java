package com.baizhi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoPo {
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    private String uploadTime;
    private String description;
    private String likeCount;
    private String userPhoto;
    private String cateName;




}
