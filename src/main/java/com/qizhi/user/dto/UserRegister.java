package com.qizhi.user.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 用户注册
 */
@Data
@ToString
public class UserRegister {

    @NotBlank(message = "手机号必须")
    @Size(min = 11, max = 11, message = "手机号必须是11位")
    private String phoneNumber;

    private Integer locationX;

    private Integer locationY;

    private String idCard;

    @NotBlank(message = "用户类型必选")
    private String userType;

}
