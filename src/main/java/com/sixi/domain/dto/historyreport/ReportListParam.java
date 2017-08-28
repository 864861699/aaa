package com.sixi.domain.dto.historyreport;

import lombok.Data;

/**
 *历史报表生成参数
 */
@Data
public class ReportListParam {
    /**
     * 业务员
     */
    private int yuy;
    /**
     * 提款人
     */
    private int tkr;
    /**
     * 付款方式
     */
    private int payment_method;
    /**
     * 最小金额
     */
    private double moneymin;
    /**
     * 最大金额
     */
    private double moneymax;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 查询类型：0公司名，1合同编号
     */
    private String keytype;
    /**
     * 一级部门
     */
    private int class1;
    /**
     * 二级部门
     */
    private int class2;
    /**
     * 合同类型
     */
    private int contract_type;
    /**
     * timetype	时间类型：1.提款日期，2.审核日期+结转，3.审核日期，4.结转
     */
    private int timetype;
    /**
     * 开始时间
     */
    private String starttime;
    /**
     * 结束时间
     */
    private String endtime;
    /**
     * 发票情况
     */
    private int invoice_state;
    /**
     * isgenerated
     */
    private int isgenerated;
}
