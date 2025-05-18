package com.qizhi.user.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userId;

    private String phoneNumber;

    private Integer locationX;

    private Integer locationY;

    private String idCard;

    private String userType;

    private String token;

    private Date expire;

}