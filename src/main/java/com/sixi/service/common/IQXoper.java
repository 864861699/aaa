package com.sixi.service.common;

public interface IQXoper {

    /**
     * 添加权限
     * @param userid 人员名
     * @param funStr 权限名
     * @return
     */
    boolean add(int userid,String funStr);
}
