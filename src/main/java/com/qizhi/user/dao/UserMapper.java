package com.qizhi.user.dao;

import com.qizhi.user.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKey(User record);

    User selectByPhone(String phoneNumber);

    User selectByToken(String token);
}