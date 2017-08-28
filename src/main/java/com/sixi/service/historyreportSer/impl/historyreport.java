package com.sixi.service.historyreportSer.impl;

import com.sixi.domain.cwmodel.ReportDir;
import com.sixi.domain.dao.cwpg.IGenerateReport;
import com.sixi.domain.dao.oasqlserver.IClass1;
import com.sixi.domain.dao.oasqlserver.IhistoryreportDao;
import com.sixi.domain.dto.historyreport.ReportListParam;
import com.sixi.service.historyreportSer.Ihistoryreport;
import com.sixi.utils.Page;
import com.sixi.domain.cwmodel.ReportDetails;
import com.sixi.domain.dao.oasqlserver.IManage;
import com.sixi.utils.Fn;
import com.sixi.utils.RD;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

/**
 * 历史报表相关实现类
 */
@Service
public class historyreport implements Ihistoryreport {
    
    @Autowired
    IhistoryreportDao ihistoryreportDao;

    @Autowired
    IGenerateReport iGenerateReport;

    @Autowired
    IManage iManage;

    @Autowired
    IClass1 iClass1;

    /**
     * 生成历史数据的逻辑层
     * @param param
     * @return
     */
    @Transactional
    @Override
    public RD getGenerateMap(ReportListParam param) {
        param.setTimetype(0);
        param.setStarttime("2017-01-01");
        param.setEndtime("2017-02-01");

        List<Map<String,Object>> list= ihistoryreportDao.getGenerate(param);
        ReportDir dir=new ReportDir();
        dir.setTimerange(Fn.toString(param.getStarttime()+"~"+param.getEndtime()));

        //生成目录
        boolean b= iGenerateReport.insertReportDir(dir);
        if(!b){
            return RD.error("生成目录失败");
        }

        List<String> contractNumList= iGenerateReport.contractNumList();

        List<Map<String,Object>> contractNumErr=new ArrayList<>();
        for (Map<String,Object> map : list){
            //如果出现重复数据直接返回
            System.out.println( Fn.toTimestamp(Fn.toString(map.get("mdate"))));
            String contractNum=Fn.toString(map.get("contract_num"));
            if(contractNumList.contains(contractNum)){
                contractNumErr.add(map);
                continue;
            }

            ReportDetails details=new ReportDetails();
            details.setClass1(Fn.toInt(map.get("class1")));
            details.setClass1name(Fn.toString(map.get("class1name")));
            details.setClass2(Fn.toInt(map.get("class2")));
            details.setClass2name(Fn.toString(map.get("class2name")));
            details.setUsername(Fn.toString(map.get("tkrname")));
            details.setUserid(Fn.toInt(map.get("tkrid")));
            details.setCustomid(Fn.toInt(map.get("customid")));
            details.setCustom_name(Fn.toString(map.get("customname")));
            details.setContract_type(Fn.toInt(map.get("contract_type")));
            details.setContract_typename(Fn.toString(map.get("clname")));
            details.setMoney(Fn.toDouble(map.get("money")));
            details.setXkzfz_uid(Fn.toInt(map.get("xkzfz_uid")));
            details.setXkzfz_name(Fn.toString(map.get("xkzfz_name")));
            details.setZakf_name(Fn.toString(map.get("kfname")));
            details.setZakf_uid(Fn.toInt(map.get("kfid")));
            details.setInvoiceid(Fn.toInt(map.get("invoiceid")));
            details.setPayment_date(Fn.toTimestamp(Fn.toString(map.get("mdate"))));
            details.setContract_start(Fn.toTimestamp(Fn.toString(map.get("startime"))));
            details.setContract_end(Fn.toTimestamp(Fn.toString(map.get("endtime"))));
            details.setContract_num(contractNum);
            details.setRemark(Fn.toString(map.get("remark")));
            details.setDirid(Fn.toInt(dir.getId()));
            details.setInvoice_state(Fn.toInt(map.get("flow")));
            iGenerateReport.insertReportDeteils(details);
        }

        return RD.success(contractNumErr);
    }

    /**
     * 获取目录集合逻辑层
     * @param param
     * @return
     */
    @Override
    public Page getListDir(int pageIdex, int pageSize) {
        List<Map<String,Object>> list= iGenerateReport.getDirList();

        return Fn.listToPage(list,1,20);
    }

    /**
     * 历史详情数据
     * @param id 目录id
     * @return
     */
    @Override
    public Map<String, Object> getDetailsList(Integer id) {
        List<Map<String,Object>> detailsList= iGenerateReport.getDetailsList(id);

        Map<Integer,String> class2Map=new HashMap<>();//集合中的二级部门
        Map<Integer,Integer> userMap=new HashMap<>();//集合中的人员集合，键为人员，值为部门
        detailsList.forEach(x-> {
            int class2=Fn.toInt(x.get("class2"));
            class2Map.put(class2,Fn.toString(x.get("class2name")));
            userMap.put(Fn.toInt(x.get("userid")),class2);
        });

        List<Map<String,Object>> totalList=new ArrayList<>();

        Map<String,Object> totalMap=new HashMap<>();
        double moneysum=0;
        //部门集合
        for (Integer class2: class2Map.keySet()) {
            Map<String,Object> class2MapList=new HashMap<>();
            List userList=new ArrayList();
            double classmoneysum=0;//部门集合小计
            //循环人员集合
            for (Integer userid: userMap.keySet()) {
                int userClass2id= Fn.toInt(userMap.get(userid));

                if(class2!=userClass2id) continue;
                Map<String,Object> userMapList=new HashMap<>();
                double usermoneysum=0;//人员集合小计
                List CustomList=new ArrayList();
                String username="";
                //循环数据集合
                for (Map<String,Object> map: detailsList) {

                    if(Fn.toInt(map.get("userid")).equals(userid)){
                        double money=Fn.toDouble(map.get("money"));
                        username=Fn.toString(map.get("username"));
                        CustomList.add(map);
                        moneysum+=money;
                        classmoneysum+=money;
                        usermoneysum+=money;
                    }
                }
                userMapList.put("usermoneysum",usermoneysum);
                userMapList.put("customList",CustomList);
                userMapList.put("username",username);
                userList.add(userMapList);
            }

            class2MapList.put("classname",class2Map.get(class2));
            class2MapList.put("classmoneysum",classmoneysum);
            class2MapList.put("userList",userList);
            totalList.add(class2MapList);
        }
        totalMap.put("moneysum",moneysum);
        totalMap.put("classList",totalList);

        return totalMap;
    }

    /**
     * 历史报表详情信息
     * @param id 单条记录id
     * @return
     */
    @Override
    public Map<String, Object> getDetailsInfo(Integer id) {
        return iGenerateReport.getDetailsInfo(id);
    }

    /**
     * 删除某一条业绩
     * @param id
     * @return
     */
    @Override
    public RD detailsdel(Integer id) {
        return RD.quick(iGenerateReport.detailsdel(id));
    }

    /**
     * 修改详情
     * @param ds 修改详情参数
     * @return
     */
    @Override
    public RD detailsEdit(ReportDetails ds) {
        return RD.quick(iGenerateReport.detailsEdit(ds)>0);
    }

    /***
     * 添加款项
     * @param id 款项添加
     * @param dirid 目录id
     * @return
     */
    @Override
    public RD moneyAdd(int id,int dirid) {
        Map<String,Object> map= ihistoryreportDao.getGenerateId(id);
        List<String> contractNumList= iGenerateReport.contractNumList();

        //如果出现重复数据直接返回
        System.out.println( Fn.toTimestamp(Fn.toString(map.get("mdate"))));
        String contractNum=Fn.toString(map.get("contract_num"));
        if(contractNumList.contains(contractNum)){
            return RD.error("该公司已经添加过！");
        }

        ReportDetails details=new ReportDetails();
        details.setClass1(Fn.toInt(map.get("class1")));
        details.setClass1name(Fn.toString(map.get("class1name")));
        details.setClass2(Fn.toInt(map.get("class2")));
        details.setClass2name(Fn.toString(map.get("class2name")));
        details.setUsername(Fn.toString(map.get("tkrname")));
        details.setUserid(Fn.toInt(map.get("tkrid")));
        details.setCustomid(Fn.toInt(map.get("customid")));
        details.setCustom_name(Fn.toString(map.get("customname")));
        details.setContract_type(Fn.toInt(map.get("contract_type")));
        details.setContract_typename(Fn.toString(map.get("clname")));
        details.setMoney(Fn.toDouble(map.get("money")));
        details.setXkzfz_uid(Fn.toInt(map.get("xkzfz_uid")));
        details.setXkzfz_name(Fn.toString(map.get("xkzfz_name")));
        details.setZakf_name(Fn.toString(map.get("kfname")));
        details.setZakf_uid(Fn.toInt(map.get("kfid")));
        details.setInvoiceid(Fn.toInt(map.get("invoiceid")));
        details.setPayment_date(Fn.toTimestamp(Fn.toString(map.get("mdate"))));
        details.setContract_start(Fn.toTimestamp(Fn.toString(map.get("startime"))));
        details.setContract_end(Fn.toTimestamp(Fn.toString(map.get("endtime"))));
        details.setContract_num(contractNum);
        details.setRemark(Fn.toString(map.get("remark")));
        details.setDirid(dirid);
        details.setInvoice_state(Fn.toInt(map.get("flow")));

        return RD.quick(iGenerateReport.insertReportDeteils(details));
    }

    /**
     * 关键字查询
     * @param keyword
     * @return
     */
    @Override
    public List<Map<String, Object>> moneyfloat(String keyword) {
        return ihistoryreportDao.getmoneyfloat(keyword);
    }

    /**
     * 详情id
     * @param id
     * @return
     */
    @Override
    public Map<String,XSSFWorkbook> excelExport(int id) {
        Map<String,Object> getDirListInfoMap= iGenerateReport.getDirListInfo(id);
        String salaryDate = Fn.toString(getDirListInfoMap.get("timerange"))+"报表导出";


        Map<String,Object> dataMap= getDetailsList(id);
        List<Map<String,Object>> classlist= (List<Map<String,Object>>) dataMap.get("classList");

        // 第一步，创建一个webbook，对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(salaryDate);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        XSSFRow row = sheet.createRow( 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();//样式设置
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        XSSFFont font = wb.createFont();//字体设置
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font.setFontHeightInPoints((short) 18);//设置字体大小

        //设置每列宽高
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 2000);
        sheet.setColumnWidth(2, 10000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(7, 5000);
        sheet.setColumnWidth(8, 5000);
        sheet.setColumnWidth(9, 3000);
        sheet.setColumnWidth(12, 3000);
        sheet.setColumnWidth(13, 3500);
        sheet.setColumnWidth(14, 3500);

        XSSFCell cell = row.createCell( 0);
        cell.setCellValue(salaryDate);
        style.setFont(font);//选择需要用到的字体格式
        cell.setCellStyle(style);

        cell = row.createCell( 1);
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,16));

        int rownum=1;//行数
        for (Map<String,Object> classmap: classlist) {
            row = sheet.createRow(rownum);
            row.createCell( 0).setCellValue(Fn.toString(classmap.get("classname")));
            row.createCell( 7).setCellValue("合计："+Fn.toDouble(classmap.get("classmoneysum")));

            rownum++;
            //获取人员集合
            List<Map<String,Object>> userList=(List<Map<String,Object>>) classmap.get("userList");
            for (Map<String,Object> usermap: userList) {
                row = sheet.createRow(rownum );

                XSSFCellStyle cellStyle = wb.createCellStyle();
                XSSFFont font2 = wb.createFont();
                font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
                XSSFCell cell1 =row.createCell( 1);

                cell1.setCellValue(Fn.toString(usermap.get("username")));
                cellStyle.setFont(font2);//选择需要用到的字体格式
                cell1.setCellStyle(cellStyle);

                XSSFCell cell4=row.createCell( 4);
                cell4.setCellValue("小计："+Fn.toDouble(usermap.get("usermoneysum")));
                cell4.setCellStyle(cellStyle);

                XSSFCell cell5=row.createCell( 5);
                cell5.setCellValue("业绩提点");
                cell5.setCellStyle(cellStyle);

                XSSFCell cell6=row.createCell( 6);
                cell6.setCellValue("开票情况");
                cell6.setCellStyle(cellStyle);

                XSSFCell cell7=row.createCell( 7);
                cell7.setCellValue("合同编号");
                cell7.setCellStyle(cellStyle);

                XSSFCell cell8=row.createCell( 8);
                cell8.setCellValue("支付方式");
                cell8.setCellStyle(cellStyle);

                XSSFCell cell9=row.createCell( 9);
                cell9.setCellValue("续开负责人");
                cell9.setCellStyle(cellStyle);

                XSSFCell cell10=row.createCell( 10);
                cell10.setCellValue("中阿客服");
                cell10.setCellStyle(cellStyle);

                XSSFCell cell11=row.createCell( 11);
                cell11.setCellValue("备注");
                cell11.setCellStyle(cellStyle);

                XSSFCell cell12=row.createCell( 12);
                cell12.setCellValue("付款日期");
                cell12.setCellStyle(cellStyle);

                XSSFCell cell13=row.createCell( 13);
                cell13.setCellValue("合同开始时间");
                cell13.setCellStyle(cellStyle);

                XSSFCell cell14=row.createCell( 14);
                cell14.setCellValue("合同结束时间");
                cell14.setCellStyle(cellStyle);
                rownum++;
                //获取公司集合
                List<Map<String,Object>> customList=(List<Map<String,Object>>) usermap.get("customList");
                for (Map<String,Object> custommap: customList) {
                    row = sheet.createRow(rownum );
                    row.createCell( 2).setCellValue(Fn.toString(custommap.get("custom_name")));
                    row.createCell( 3).setCellValue(Fn.toString(custommap.get("contract_typename")));
                    row.createCell( 4).setCellValue(Fn.toDouble(custommap.get("money")));
                    row.createCell( 5).setCellValue(Fn.toDouble(custommap.get("tidian")));
                    row.createCell( 6).setCellValue(Fn.toInt(custommap.get("invoice_state")));
                    row.createCell( 7).setCellValue(Fn.toString(custommap.get("contract_num")));
                    row.createCell( 8).setCellValue(Fn.toString("支付方式"));
                    row.createCell( 9).setCellValue(Fn.toString(custommap.get("xkzfz_name")));
                    row.createCell( 10).setCellValue(Fn.toString(custommap.get("zakf_name")));
                    row.createCell( 11).setCellValue(Fn.toString(custommap.get("remark")));
                    row.createCell( 12).setCellValue(Fn.toString(custommap.get("payment_date")));
                    row.createCell( 13).setCellValue(Fn.toString(custommap.get("contract_start")));
                    row.createCell( 14).setCellValue(Fn.toString(custommap.get("contract_end")));
                    rownum++;
                }
            }
        }

        Map returnMap=new HashMap<>();
        returnMap.put(salaryDate,wb);
        return returnMap;
    }


}
