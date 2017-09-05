package com.sixi.service.common;

/**
 * 权限接口
 */
public interface IQXHelper {

    /**
     * 根据人员判断权限
     * @param userid  人员id
     * @param funStr  权限名
     * @return
     */
    boolean check(int userid,String funStr);

    /**
     * 当前登录人权限
     * @param funStr 权限名
     * @return
     */
    boolean check(String funStr);

    /**
     * 添加权限
     * @param userid 人员名
     * @param funStr 权限名
     * @return
     */
    boolean add(int userid,String funStr);
}
