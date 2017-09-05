package com.sixi.aop;


import com.alibaba.fastjson.JSON;
import com.sixi.utils.RD;
import com.sixi.utils.Session;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录验证
 */
@Component
@Aspect
public class LoginVerification {

	@Pointcut("within(com.sixi.api..*) && "
			+ "!within(com.sixi..api.nologin..*)")
	public void aop(){}
	

	@Around("aop()")
	public Object watchPerformance(ProceedingJoinPoint jp) throws IOException {
		String ctrlName = jp.getTarget().getClass().getSimpleName();
        String actionName = jp.getSignature().getName();

        Integer userID = Session.getSession("userid");
		String userName = Session.getSession("username");
		if(userID!=null && userID.intValue()>0){

			try {
				return jp.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
				return JSON.toJSONString(RD.exception("system error:"+e.getMessage()));
				//return JSON.toJSONString(new RD(500, "system error:"+e.getMessage(), null));
			}
		}else{
			return RD.notlogin();
//			return new RD(401, "no login", null);
		}
	}
}
