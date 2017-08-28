package com.sixi.service.common;

import org.springframework.stereotype.Service;

@Service
public interface IUserLogin {
    /**
     * 用户登录
     * @param username
     * @param userpwd
     * @return 返回userid
     */
    boolean login(String username, String userpwd);

    /**
     * 用户退出
     */
    void loginOut();
}
