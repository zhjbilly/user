package com.qizhi.user.facade.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qizhi.user.dto.UserDTO;
import com.qizhi.user.facade.UserFacade;
import com.qizhi.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Override
    public UserDTO validToken(String token) {
        return userService.tokenValid(token);
    }
}
