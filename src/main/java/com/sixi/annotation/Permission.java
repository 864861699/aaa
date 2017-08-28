package com.sixi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA
 * 权限检测注解
 *
 * @author 喵♂呜
 * Created on 2017/7/4 10:57.
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface Permission {
    /**
     * @return 权限名称
     */
    String value();

    /**
     * @return 是否启用
     */
    boolean enable() default true;
}

