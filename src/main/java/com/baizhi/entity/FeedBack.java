package com.baizhi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBack implements Serializable {
    private String id;
    private String title;
    private String content;
    private  String userId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date feedTime;
    //关系属性
    @Transient
    private User user;
}
