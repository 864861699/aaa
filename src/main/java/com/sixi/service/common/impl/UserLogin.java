package com.sixi.service.common.impl;

import com.sixi.domain.dao.oasqlserver.IManage;
import com.sixi.domain.oamodel.Manage;
import com.sixi.service.common.IQXHelper;
import com.sixi.service.common.IUserLogin;
import com.sixi.utils.Fn;
import com.sixi.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserLogin implements IUserLogin {

	@Autowired
	IManage iManage;

	@Autowired
	IQXHelper iqxHelper;

	@Override
	public boolean login(String username, String userpwd) {
		userpwd= Fn.md5_16(userpwd);
		System.out.println(username);
		System.out.println(userpwd);
		Manage info = iManage.getInfo(username, userpwd);
		if(info==null){
			System.out.println("账号密码错误");
			return false;
		}

		boolean b= iqxHelper.check(11,"");
		
		int userID=info.getId();
		if (userID>0) {
			System.out.println("设置Session成功");
			Session.setSession("userid", userID);
			Session.setSession("username", info.getUsername());
			Session.setSession("class1", info.getAdmin());
			Session.setSession("class2", info.getClass2());
			Session.setSession("manager", info.getManager());
			return true;
		}
		return false;
	}

	@Override
	public void loginOut() {
		Session.getSession().invalidate();
		System.out.println("清空Session");
	}
}
