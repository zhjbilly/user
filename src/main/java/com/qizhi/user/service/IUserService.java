package com.qizhi.user.service;

import com.qizhi.user.dto.UserDTO;

public interface IUserService {

    /**
     * 员工注册
     *
     * @param phoneNumber
     * @param idCard
     */
    void registerStaff(String phoneNumber, String idCard);

    /**
     * 顾客注册
     *
     * @param phoneNumber
     * @param locationX
     * @param locationY
     */
    void registerCust(String phoneNumber, int locationX, int locationY);

    /**
     * 用户登录
     *
     * @param phoneNumber
     * @return
     */
    UserDTO login(String phoneNumber);

    /**
     * token有效性
     *
     * @param token
     * @return
     */
    UserDTO tokenValid(String token);
}
