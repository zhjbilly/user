package com.qizhi.user.facade;

import com.qizhi.user.dto.UserDTO;

public interface UserFacade {

    /**
     * 校验用户token
     * @param token
     * @return
     */
    UserDTO validToken(String token);
}
