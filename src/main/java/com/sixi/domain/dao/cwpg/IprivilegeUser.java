package com.sixi.domain.dao.cwpg;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户权限
 */
@Repository
public interface IprivilegeUser {

    /**
     * 查看所有权限
     * @return
     */
    List<Map<String,Object>> gitList();

    /**
     * 权限添加
     * @param userid 人员id
     * @param privilegeName 权限名
     * @return
     */
    boolean add(@Param("userid") int userid, @Param("funStr") String funStr);

    /**
     * 修改权限
     * @param id 权限id
     * @param userid 添加人员
     * @param privilegeName 权限名
     * @return
     */
    int edit(int id,int userid,String privilegeName);

    /**
     * 权限详情
     * @param id
     * @return
     */
    Map<String,Object> info(int id);

    /**
     * 删除权限
     * @param id
     * @return
     */
    int del(int id);
}
