package com.sixi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sixi.annotation.Permission;
import com.sixi.service.common.IQXHelper;
import com.sixi.service.common.impl.QXHelper;
import com.sixi.utils.Fn;
import com.sixi.utils.RD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.security.pkcs11.wrapper.Constants;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限拦截
 * @Author 艾翔
 * @Date 2017/8/26 15:48
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    IQXHelper qXHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        boolean b=true;
        if (o != null) {
            Permission permission = ((HandlerMethod) o).getMethodAnnotation(Permission.class);
            if(permission != null){
                String funStr =Fn.toString(permission.value());
                if ( funStr.length()>0) {
                    b= qXHelper.check(funStr);
                }
            }
        }

        if(!b){
            response.getOutputStream().write(JSON.toJSONString(RD.error("无权限访问")).getBytes("UTF-8"));
            return false;
        }

        return true;
    }
}

