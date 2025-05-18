package com.qizhi.user.controller;

import com.qizhi.user.constant.CommonResult;
import com.qizhi.user.constant.ExceptionCodeEnum;
import com.qizhi.user.constant.UserType;
import com.qizhi.user.dto.UserDTO;
import com.qizhi.user.dto.UserLogin;
import com.qizhi.user.dto.UserRegister;
import com.qizhi.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * user
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public CommonResult<Void> register(@Validated UserRegister user){
        if(UserType.STAFF.name().equals(user.getUserType())){
            // 员工
            if(StringUtils.isBlank(user.getIdCard()) || 18 != user.getIdCard().length()){
                log.warn("身份证格式有误, {}", user);
               return CommonResult.failed(ExceptionCodeEnum.VALIDATE_FAILED);
            }
            userService.registerStaff(user.getPhoneNumber(), user.getIdCard());
        }else if(UserType.CUSTOMER.name().equals(user.getUserType())){
            // 客户
            if(user.getLocationX() < 0 || user.getLocationX() > 100 || user.getLocationY() < 0 || user.getLocationY() > 100){
                log.warn("地理位置有误, {}", user);
                return CommonResult.failed(ExceptionCodeEnum.VALIDATE_FAILED);
            }
            userService.registerCust(user.getPhoneNumber(), user.getLocationX(), user.getLocationY());
        }else{
            log.warn("参数有误, {}", user);
            return CommonResult.failed(ExceptionCodeEnum.VALIDATE_FAILED);
        }
        return CommonResult.success();
    }

    /**
     * 用户登陆
     */
    @PostMapping("/login")
    public CommonResult<String> login(@Valid UserLogin user){
        UserDTO login = userService.login(user.getPhoneNumber());
        if(null == login){
            return CommonResult.failed(ExceptionCodeEnum.UNAUTHORIZED);
        }
        return CommonResult.success(login.getToken());
    }

}
