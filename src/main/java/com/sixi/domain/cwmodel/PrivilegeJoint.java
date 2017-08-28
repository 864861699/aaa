package com.sixi.domain.cwmodel;

import lombok.Data;

/**
 * 联合权限模型
 */
@Data
public class PrivilegeJoint {
    private int id;
    /**
     * 一级部门id
     */
    private int class1id;
    /**
     * 二级部门id
     */
    private int class2id;
    /**
     * 权限名
     */
    private String privilege_name;
    /**
     *职位
     */
    private int post;
}
