package com.sixi.api.common;

import com.sixi.service.common.IQXHelper;
import com.sixi.utils.RD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 权限控制器
 */
@RestController
@RequestMapping("/common/qx")
public class QXCtr {

    @Autowired
    IQXHelper iqxHelper;

    /**
     * 判断是否有权限
     * @param funStr 权限名
     * @Author 艾翔
     * @Date 2017/8/28 15:47
     */
    @RequestMapping("/isqx")
    public RD notFound(String funStr) {
        return RD.quick(iqxHelper.check(funStr));
    }

    /**
     * 添加人员权限
     * @param userid 人员id
     * @param funStr 权限名
     * @Author 艾翔
     * @Date 2017/8/28 15:47
     */
    @RequestMapping("/adduser")
    public RD adduser(int userid,String funStr) {
        return RD.quick(iqxHelper.add(userid,funStr));
    }

}
