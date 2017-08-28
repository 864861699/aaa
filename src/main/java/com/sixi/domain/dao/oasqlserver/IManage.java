package com.sixi.domain.dao.oasqlserver;

import com.sixi.domain.oamodel.Manage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IManage {

	Manage getInfo(@Param("username") String username, @Param("pswd") String pswd);

	/**
	 * 获取人员的一级部门，二级部门
	 * @param id 人员id
	 * @return
	 */
	Map<String,Object> getManageClass(@Param("id") int id);

	/**
	 * 获取人员集合
	 * @return
	 */
	List<Manage> getList();

}
