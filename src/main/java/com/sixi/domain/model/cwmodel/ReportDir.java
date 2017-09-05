package com.sixi.domain.model.cwmodel;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReportDir {
    /**
     * 主键
     */
    private int id;
    /**
     * 添加时间
     */
    private Timestamp addtime;
    /**
     * 付款管理时间区间
     */
    private String timerange;
    /**
     * 工资年月
     */
    private String salary_yearmonth;
    /**
     * 审核状态：0 默认、1 审核通过、 2审核不通过
     */
    private int fincheck;
    /**
     * 审核时间
     */
    private Timestamp fincheck_time;
    /**
     * 生成类型：1提款日期、2审核日期+结转、3审核日期、4.结转
     */
    private int type;
}
