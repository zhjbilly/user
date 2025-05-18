package com.qizhi.user.service.impl;

import com.qizhi.user.constant.UserType;
import com.qizhi.user.dao.UserMapper;
import com.qizhi.user.domain.User;
import com.qizhi.user.dto.UserDTO;
import com.qizhi.user.service.IUserService;
import com.qizhi.user.util.BizException;
import com.qizhi.user.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserService implements IUserService {

    /**
     * 默认失效时间
     */
    public static final int EXPIRE_TIME = 1;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void registerStaff(String phoneNumber, String idCard){
        // 手机号校验
        User existUser = userMapper.selectByPhone(phoneNumber);
        if (null != existUser) {
            throw new BizException("手机号已注册，请勿重复注册!");
        }
        // 注册
        User user = new User();
        user.setUserType(UserType.STAFF.name());
        user.setIdCard(idCard);
        user.setPhoneNumber(phoneNumber);
        if(1 > userMapper.insert(user)){
            throw new RuntimeException("手机号注册失败,请联系管理员");
        }
    }

    @Override
    public void registerCust(String phoneNumber, int locationX, int locationY){
        // 手机号校验
        User existUser = userMapper.selectByPhone(phoneNumber);
        if (null != existUser) {
            throw new BizException("手机号已注册，请勿重复注册!");
        }
        // 注册
        User user = new User();
        user.setUserType(UserType.STAFF.name());
        user.setLocationX(locationX);
        user.setLocationY(locationY);
        user.setPhoneNumber(phoneNumber);
        if(1 > userMapper.insert(user)){
            throw new RuntimeException("手机号注册失败,请联系管理员");
        }
    }

    @Override
    public UserDTO login(String phoneNumber){
        // 手机号校验
        User existUser = userMapper.selectByPhone(phoneNumber);
        if (null == existUser) {
            throw new BizException("手机号未注册，请先注册!");
        }
        // 用户登录
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(existUser.getUserId());
        userDTO.setPhoneNumber(existUser.getPhoneNumber());
        userDTO.setToken(UUID.randomUUID().toString());
        userDTO.setExpire(DateUtils.addMinutes(DateUtils.getNowDate(), EXPIRE_TIME));
        if(1 > userMapper.updateByPrimaryKey(existUser)){
            throw new RuntimeException("登陆失败,请联系管理员");
        }
        return userDTO;
    }

    @Override
    public UserDTO tokenValid(String token) {
        User existUser = userMapper.selectByToken(token);
        if(null == existUser || DateUtils.getNowDate().after(existUser.getExpire())){
            log.warn("用户不存在或者token已失效");
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserType(existUser.getUserType());
        userDTO.setIdCard(existUser.getIdCard());
        userDTO.setLocationX(existUser.getLocationX());
        userDTO.setLocationY(existUser.getLocationY());
        userDTO.setPhoneNumber(existUser.getPhoneNumber());
        return userDTO;
    }
}
