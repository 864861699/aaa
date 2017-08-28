package com.sixi.service.common.impl;

import com.sixi.domain.dao.cwpg.IprivilegeJoint;
import com.sixi.domain.dao.cwpg.IprivilegeUser;
import com.sixi.service.common.IQXoper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QXoper implements IQXoper{

    @Autowired
    IprivilegeUser iprivilegeUser;

    @Autowired
    IprivilegeJoint iprivilegeJoint;

    @Override
    public boolean add(int userid, String funStr) {
        return iprivilegeUser.add(userid,funStr);
    }
}
