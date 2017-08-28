package com.sixi.domain.dto.historyreport;

import lombok.Data;

/**
 * 历史报表详情修改参数
 */
@Data
public class datailsSaveParam {

    private int id;
    /**
     *合同结束时间
     */
    private String contract_end;
    /**
     * 合同开始时间
     */
    private String contract_start;
    /**
     * 合同类型
     */
    private int contract_type;
    /**
     *公司名
     */
    private String customname;
    /**
     * 发票状态
     */
    private int invoice_state;
    /**
     * 款项金额
     */
    private double money;
    /**
     * 付款时间
     */
    private String money_addtime;
    /**
     *付款方式
     */
    private int payment_method;
    /**
     * 备注
     */
    private String remark;
    /**
     * 提点数
     */
    private double tdnum;
    /**
     * 提款人
     */
    private int tkr;
    /**
     * 续开总负责
     */
    private int xkzfz;
    /**
     * 中阿客服
     */
    private int znkf;

}
