package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "yx_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String phone;
    @Column(name = "head_img")
    private String headImg;

    private String name;

    private String sign;

    private String wechat;

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "regist_time")
    private Date registTime;
    @Transient
    private List<FeedBack> list;

}