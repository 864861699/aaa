package com.sixi.interceptor;

import com.sixi.annotation.Permission;
import com.sixi.utils.Fn;
import com.sixi.utils.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志输入
 * @Author 艾翔
 * @Date 2017/8/26 15:27
 */
@Slf4j
public class DebugInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("--------------------------------------------------------------------------------");
        if (handler instanceof HandlerMethod) {
            Class cc = ((HandlerMethod) handler).getMethod().getDeclaringClass();
            StringBuffer str = new StringBuffer().append(cc.getName()).append(".(").append(cc.getSimpleName()).append(".java:1)");
            log.info("Controller  : "+ str.toString());
            log.info("Method      : "+((HandlerMethod) handler).getMethod().getName());
        }

        log.info("Path        : " + request.getServletPath());
        log.info("Method      : " + request.getMethod());

        String funStr ="";
        if (handler != null) {
            Permission permission = ((HandlerMethod) handler).getMethodAnnotation(Permission.class);
            if(permission!=null){
                funStr = permission.value();
            }
        }

        if(funStr.length()>0){
            log.info("FunStr      : "+funStr);
        }


        String getParam = Fn.toString(request.getQueryString());
        if(getParam.length()>0) {
            String[] getParamArr = getParam.split("&");
            String paramStr = "Param       : ";

            for (String s : getParamArr) {
                paramStr += s + "  ";
            }

            log.info(paramStr);
        }
        Integer userID = Session.getSession("userid");
        String userName = Session.getSession("username");
        int class1 = Session.getSession("class1");
        int class2 = Session.getSession("class2");
        log.info("--------------------------------------------------------------------------------");
    }
}
