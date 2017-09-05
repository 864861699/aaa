package com.sixi.utils;

public class RD {
    private int state;
    private String msg;
    private Object data;

    private static final int success=200;//服务端操作成功
    private static final int error=202;//服务端操作失败
    private static final int errorPath=404;//失败路径
    private static final int exception=500;//服务端报错
    private static final int permissions=403;//无访问权限
    private static final int notlogin=401;//未登录
    
    /**
     * 未登录
     * @return
     */
    public static RD notlogin(){return new RD(notlogin,"未登录","");}

    /**
     * 报错情况
     * @param msg
     * @return
     */
    public static RD exception(String msg){return new RD(exception,msg,"");}

    /**
     * 获取返回状态的实例
     *
     * @param state
     * @param msg
     * @param data
     * @return
     */
    public static RD getInstance(int state, String msg, Object data) {
        return new RD(state, msg, data);
    }

    /**
     * 返回错误处理
     *
     * @param msg
     * @param data
     * @return
     */
    public static RD error(String msg, Object data) {
        return new RD(error, msg, data);
    }

    /**
     * 返回错误处理(data 默认为 null)
     *
     * @param msg
     * @param data
     * @return
     */
    public static RD error(String msg) {
        return new RD(error, msg, null);
    }

    /**
     * 成功处理
     *
     * @param msg
     * @param data
     * @return
     */
    public static RD success(String msg, Object data) {
        return new RD(success, msg, data);
    }

    /**
     * 成功处理(msg 默认为 "")
     *
     * @param msg
     * @param data
     * @return
     */
    public static RD success(Object data) {
        return new RD(success, "", data);
    }

    /**
     * 成功处理(msg 默认为 ""，data默认为null)
     *
     * @param msg
     * @param data
     * @return
     */
    public static RD success() {
        return new RD(success, "", null);
    }

    /**
     * 快速或敏捷返回数据
     *
     * @param b
     * @return
     */
    public static RD quick(boolean b) {
        if (b) {
            return new RD(success, "", null);
        } else {
            return new RD(error, "失败", null);
        }
    }

    /**
     * 快速或敏捷返回数据
     *
     * @param b
     * @param errorMsg 若异常时，返回的消息
     * @return
     */
    public static RD quick(boolean b, String errorMsg) {
        if (b) {
            return new RD(success, "", null);
        } else {
            return new RD(error, errorMsg, null);
        }
    }

    /**
     * @param b
     * @param successData 若成功时，返回的数据
     * @return
     */
    public static RD quick(boolean b, Object successData) {
        if (b) {
            return new RD(success, "", successData);
        } else {
            return new RD(error, "失败", null);
        }
    }

    /**
     * @param b
     * @param errorMsg    若异常时，返回的消息
     * @param successData 若成功时，返回的数据
     * @return
     */
    public static RD quick(boolean b, String errorMsg, Object successData) {
        if (b) {
            return new RD(success, "", successData);
        } else {
            return new RD(error, errorMsg, null);
        }
    }

    /**
     * 状态 0：失败或错误 1：成功
     *
     * @return
     */
    public int getState() {
        return state;
    }

    /**
     * 消息内容
     *
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 返回数据
     *
     * @return
     */
    public Object getData() {
        return data;
    }

    /**
     * 创建实例
     *
     * @param state
     * @param msg
     * @param data
     */
    public RD(int state, String msg, Object data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

}
