package com.sixi.api.nologin;

import com.sixi.annotation.Permission;
import com.sixi.service.common.IUserLogin;
import com.sixi.utils.RD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;

@RequestMapping("/sys")
@RestController
public class SysCtrl {
    @Autowired
    IUserLogin ser;

    @RequestMapping("/login")
    public RD login(String username, String userpwd) {
        boolean result = ser.login(username, userpwd);
        return RD.success(result);
    }

    @RequestMapping("/logOut")
    public RD logout() {
        ser.loginOut();
        return RD.success(true);
    }
}
