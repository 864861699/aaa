package com.sixi.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel操作类
 * @Author 艾翔
 * @Date 2017/8/28 14:41
 */
public class ExcelUtil {

    /**
     * excel导出
     * @param salaryDate 文件名
     * @param response
     * @param wb Excel文件类
     */
    public void excelExport(String salaryDate, HttpServletResponse response,XSSFWorkbook wb){
        OutputStream output;
        if(salaryDate!=""){
            response.reset(); //清除buffer缓存
            // 指定下载的文件名
            response.setHeader("Content-Disposition", "attachment;filename="+salaryDate+".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            //导出Excel对象

            try {
                output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                bufferedOutPut.flush();
                wb.write(bufferedOutPut);
                bufferedOutPut.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Excel文件导出失败");
            }
        }
    }

}