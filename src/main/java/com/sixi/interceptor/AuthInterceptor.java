//package com.sixi.interceptor;
//
//import com.alibaba.fastjson.JSON;
//import com.sixi.utils.Fn;
//import com.sixi.utils.RD;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 登录验证
// * @Author 艾翔
// * @Date 2017/8/26 15:48
// */
//public class AuthInterceptor extends HandlerInterceptorAdapter {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        if (Fn.getLoginUserId() >0) {
//            return true;
//        }
//        String urlPath= request.getServletPath();
//        if(urlPath.equals("/error")){
//            response.getOutputStream().write(JSON.toJSONString(RD.error("你打开的页面不存在")).getBytes("UTF-8"));
//        }else {
//            response.getOutputStream().write(JSON.toJSONString(RD.error("未登录系统请先登录！")).getBytes("UTF-8"));
//        }
//        return false;
//    }
//}
