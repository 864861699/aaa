package com.sixi.interceptor;

import com.alibaba.fastjson.JSON;
import com.sixi.annotation.Permission;
import com.sixi.utils.Fn;
import com.sixi.utils.RD;
import com.sixi.utils.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * debug输入
 * @Author 艾翔
 * @Date 2017/8/26 15:27
 */
@Slf4j
public class DebugInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        StringBuilder sb = (new StringBuilder("\nFin_API2 action report -------- ")).append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())).append(" ------------------------------\n");

        if (handler instanceof HandlerMethod) {
            Class cc = ((HandlerMethod) handler).getMethod().getDeclaringClass();
            StringBuffer str = new StringBuffer().append(cc.getName()).append(".(").append(cc.getSimpleName()).append(".java:1)");
            sb.append("Controller  : ").append(cc.getName()).append(".(").append(cc.getSimpleName()).append(".java:1)");
            sb.append("\nMethod      : ").append(((HandlerMethod) handler).getMethod().getName()).append("\n");
        }

        String urlPath=request.getServletPath();
        if (urlPath != null) {
            sb.append("UrlPara     : ").append(urlPath).append("\n");
        }
        sb.append("method      : ").append(request.getMethod()).append("\n");

        String getParam = Fn.toString(request.getQueryString());
        if(getParam.length()>0) {
            String[] getParamArr = getParam.split("&");
            String paramStr = "Param       : ";

            for (String s : getParamArr) {
                paramStr += s + "  ";
            }

            sb.append(paramStr);
            sb.append("\n");
        }

        sb.append("----------------------------------------------------------------------------------\n");
        System.out.print(sb.toString());

//        log.info("--------------------------------------------------------------------------------");
//        if (handler instanceof HandlerMethod) {
//            Class cc = ((HandlerMethod) handler).getMethod().getDeclaringClass();
//            StringBuffer str = new StringBuffer().append(cc.getName()).append(".(").append(cc.getSimpleName()).append(".java:1)");
//            log.info("Controller  : "+ str.toString());
//            log.info("Method      : "+((HandlerMethod) handler).getMethod().getName());
//        }
//        String urlPath=request.getServletPath();
//        log.info("Path        : " + urlPath);
//        log.info("Method      : " + request.getMethod());
//
//        String funStr ="";
//        if (handler != null) {
//            Permission permission = ((HandlerMethod) handler).getMethodAnnotation(Permission.class);
//            if(permission!=null){
//                funStr = permission.value();
//            }
//        }
//
//        if(funStr.length()>0){
//            log.info("FunStr      : "+funStr);
//        }
//
//
//        String getParam = Fn.toString(request.getQueryString());
//        if(getParam.length()>0) {
//            String[] getParamArr = getParam.split("&");
//            String paramStr = "Param       : ";
//
//            for (String s : getParamArr) {
//                paramStr += s + "  ";
//            }
//
//            log.info(paramStr);
//        }
//        log.info("--------------------------------------------------------------------------------");
    }
}
