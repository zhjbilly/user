package com.qizhi.user.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 登陆
 */
@Data
@ToString
public class UserLogin {

    @NotBlank
    @Size(min = 11, max = 11)
    private String phoneNumber;
}
