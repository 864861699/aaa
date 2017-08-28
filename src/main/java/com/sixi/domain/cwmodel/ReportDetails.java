package com.sixi.domain.cwmodel;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReportDetails {
    /**
     * 主键
     */
    private int id;
    /**
     * 目录表id
     */
    private int dirid;
    /**
     * 公司名
     */
    private String custom_name;
    /**
     * 小部门id
     */
    private int class2;
    /**
     * 小部门名
     */
    private String class2name;
    /**
     * 一级部门id
     */
    private int class1;
    /**
     * 一级部门名
     */
    private String class1name;
    /**
     * 人员id
     */
    private int userid;
    /**
     * 人员名
     */
    private String username;
    /**
     * 公司id
     */
    private int customid;
    /**
     * 合同类型
     */
    private int contract_type;
    /**
     * 合同类型名
     */
    private String contract_typename;
    /**
     * 合同金额
     */
    private double money;
    /**
     * 业绩提点
     */
    private double tidian;
    /**
     * 续开总负责人员id
     */
    private int xkzfz_uid;
    /**
     * 续开总负责人名
     */
    private String xkzfz_name;
    /**
     * 中阿客服id
     */
    private int zakf_uid;
    /**
     * 中阿客服
     */
    private String zakf_name;
    /**
     * 发票情况
     */
    private int invoiceid;
    /**
     * 合同编号
     */
    private String contract_num;
    /**
     * 付款日期
     */
    private Timestamp payment_date;
    /**
     * 合同开始时间
     */
    private Timestamp contract_start;
    /**
     * 合同结束时间
     */
    private Timestamp contract_end;
    /**
     * 备注
     */
    private String remark;
    /**
     *发票状态
     */
    private  int invoice_state;
}
