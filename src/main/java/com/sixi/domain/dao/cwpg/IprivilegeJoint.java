package com.sixi.domain.dao.cwpg;

import com.sixi.domain.cwmodel.PrivilegeJoint;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联合权限
 */
@Repository
public interface IprivilegeJoint {

    /**
     *查看是否有权限
     * @param userid
     * @param privilegeName
     * @return
     */
    List<PrivilegeJoint> getManageJoint();

    /**
     * 添加联合权限
     * @param privilegeJoint
     * @return
     */
    boolean add(PrivilegeJoint privilegeJoint);

    /**
     * 修改权限
     * @param privilegeJoint 联合权限模型
     * @return
     */
    int edit(PrivilegeJoint privilegeJoint);

    /**
     * 查询联合权限集合
     * @return
     */
    List<PrivilegeJoint> getList();

    /**
     * 删除联合权限
     * @param id
     * @return
     */
    int del(int id);

    /**
     *查看联合权限详情
     * @param id
     * @return
     */
    PrivilegeJoint info(int id);

}
