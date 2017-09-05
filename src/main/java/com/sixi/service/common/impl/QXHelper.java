package com.sixi.service.common.impl;

import com.sixi.domain.dao.cwpg.IprivilegeJoint;
import com.sixi.domain.dao.cwpg.IprivilegeUser;
import com.sixi.domain.dao.oasqlserver.IManage;
import com.sixi.domain.model.cwmodel.PrivilegeJoint;
import com.sixi.domain.model.oamodel.Manage;
import com.sixi.service.common.IQXHelper;
import com.sixi.utils.Fn;
import com.sixi.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 艾翔
 * @Author 艾翔
 * @Date 2017/8/26 15:57
 */
@Service
public class QXHelper implements IQXHelper {
//public class QXHelper {//implements IQXHelper {

    @Autowired
    IprivilegeJoint iprivilegeJoint;

    @Autowired
    IprivilegeUser iprivilegeUser;

    @Autowired
    IManage iManage;

    /**
     * 权限判断
     * @param userid 用户id
     * @param funStr 权限名
     * @Author 艾翔
     * @Date 2017/8/26 15:56
     */
    @Cacheable(value="userCache")
    public boolean check(int userid,String funStr){
        return getcheck(userid,funStr);
    }

    /**
     *权限判断
     * @param funStr 权限名
     * @Author 艾翔
     * @Date 2017/8/26 15:56
     */
    @Cacheable(value="userCache")
    public boolean check(String funStr){
        return getcheck(Fn.getLoginUserId(),funStr);
    }

    /**
     * 添加人员权限
     * @param userid 人员名
     * @param funStr 权限名
     * @return
     */
    @Override
    public boolean add(int userid, String funStr) {
        System.out.println(userid);
        return iprivilegeUser.add(userid,funStr);
    }

    /**
     * 权限实现方法
     * @param userid 用户id
     * @param funStr 权限名
     * @Author 艾翔
     * @Date 2017/8/26 15:56
     */
    private boolean getcheck(int userid,String funStr){
        if(Fn.toString(funStr).equals("")) return true;

        List<PrivilegeJoint> joint= iprivilegeJoint.getList();
        List<Map<String, Object>> userCheck = iprivilegeUser.gitList();
        int manager=0;
        int class1=0;
        int class2=0;

        //如果登录人是权限判断的当前人员，直接获取Session中的值，不然再去数据库查询
        if(userid!=Fn.getLoginUserId()){
            List<Manage> manageList= iManage.getList();
            //人员集合
            for (Manage manage : manageList) {
                if(manage.getId()==userid){
                    manager=manage.getManager();
                    class1=manage.getAdmin();
                    class2=manage.getClass2();
                }
            }
        }else{
            manager=Session.getSession("manager");
            class1=Fn.getLoginClass1();
            class2=Fn.getLoginClass2();
        }


        //人员权限
        for (Map<String, Object> map : userCheck) {
            if(Fn.toInt(map.get("userid"))==userid && Fn.toString(map.get("privilege_name")).equals(funStr)){
                System.out.println("aaaaaaaaaaaa");
                return true;
            }
        }

        //联合权限
        for (PrivilegeJoint privilegeJoint : joint) {
            if(class1==privilegeJoint.getClass1id()
                    && class2==privilegeJoint.getClass2id()
                    && manager==privilegeJoint.getPost()
                    && privilegeJoint.getPrivilege_name().equals(funStr)){
                System.out.println("bbbbbbbbbbb");
                return true;
            }
        }

        return false;
    }
}
