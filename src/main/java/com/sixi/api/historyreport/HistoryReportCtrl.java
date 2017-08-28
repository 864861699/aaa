package com.sixi.api.historyreport;

import com.sixi.annotation.Permission;
import com.sixi.domain.dto.historyreport.ReportListParam;
import com.sixi.service.historyreportSer.Ihistoryreport;
import com.sixi.domain.cwmodel.ReportDetails;
import com.sixi.utils.ExcelUtil;
import com.sixi.utils.RD;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RequestMapping("/historyreport/historyreport")
@RestController
public class HistoryReportCtrl {

    @Autowired
    Ihistoryreport historyreport;

    /**
     * 生成历史报表数据接口
     * @param username
     * @param userpwd
     * @return
     */
    @RequestMapping("/generate")
    public RD generateData(String username, String userpwd){

        return historyreport.getGenerateMap(new ReportListParam());
    }

    /**
     * 查看目录接口
     * @return
     */
    @RequestMapping("/dir")
    public RD getDirList(){

        return RD.success(historyreport.getListDir(1,20));
    }

    /**
     * 目录详情数据接口
     * @return
     */
    @Permission("as")
    @RequestMapping("/details")
    public RD getDetailsList(){
        return RD.success(historyreport.getDetailsList(47));
    }

    /**
     * 获取单条数据详情接口
     * @return
     */
    @RequestMapping("/Info")
    public RD getDetailsInfo(){

        return RD.success(historyreport.getDetailsInfo(210));
    }

    /**
     * 删除详情信息接口
     * @return
     */
    @RequestMapping("/del")
    public RD detailsdel(){

        return historyreport.detailsdel(210);
    }

    /**
     * 修改数据接口
     * @return
     */
    @RequestMapping("/edit")
    public RD detailsedit(){

        ReportDetails ds=new ReportDetails();
        return historyreport.detailsEdit(ds);
    }

    /**
     * 添加款项接口
     * @return
     */
    @RequestMapping("/add")
    public RD moneyAdd(){

        return historyreport.moneyAdd(1,47);
    }

    /**
     * 款项浮动层
     * @return
     */
    @RequestMapping("/getmoneyfloat")
    public RD getmoneyfloat(){
        return RD.success(historyreport.moneyfloat(""));
    }

    /**
     * 导出接口
     * @return
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){
        Map<String,XSSFWorkbook> map= historyreport.excelExport(47);

        String salaryDate="";//文件名
        XSSFWorkbook wb=null;//Excel文件

        for (String str : map.keySet()) {
            salaryDate=str;
            wb=map.get(str);
        }

        new ExcelUtil().excelExport(salaryDate,response,wb);
    }

}
