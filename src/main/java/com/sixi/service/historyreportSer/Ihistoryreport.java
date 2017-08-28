package com.sixi.service.historyreportSer;

import com.sixi.domain.dto.historyreport.ReportListParam;
import com.sixi.utils.Page;
import com.sixi.domain.cwmodel.ReportDetails;
import com.sixi.utils.RD;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface Ihistoryreport {

    /**
     * 生成历史报表数据
     * @param param
     * @return
     */
    RD getGenerateMap(ReportListParam param);

    /**
     *目录接口
     */
    Page getListDir(int pageIndex, int pageSize);

    /**
     * 业绩详情接口
     * @param id 目录id
     * @return
     */
    Map<String,Object> getDetailsList(Integer id);

    /**
     * 详细业绩单条记录
     * @param id 单条记录id
     * @return
     */
    Map<String,Object> getDetailsInfo(Integer id);

    /**
     * 删除单条详情数据
     * @param id
     * @return
     */
    RD detailsdel(Integer id);

    /**
     * 修改历史报表
     * @param ds 修改详情参数
     * @return
     */
    RD detailsEdit(ReportDetails ds);

    /**
     * 款项添加
     * @param id 款项添加
     * @param dirid 目录id
     * @return
     */
    RD moneyAdd(int id,int dirid);

    /**
     * 款项浮动层
     * @return
     */
    List<Map<String,Object>> moneyfloat(String keyword);

    /**
     *excel导出文件
     * @param id
     * @return
     */
    Map<String,XSSFWorkbook> excelExport(int id);
}
