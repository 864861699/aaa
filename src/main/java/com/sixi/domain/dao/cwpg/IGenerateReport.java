package com.sixi.domain.dao.cwpg;

import com.sixi.domain.cwmodel.ReportDetails;
import com.sixi.domain.cwmodel.ReportDir;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IGenerateReport {

    /**
     * 插入目录
     * @param dir
     * @return
     */
    boolean insertReportDir(ReportDir dir);

    /**
     * 插入详情
     * @param details
     * @return
     */
    boolean insertReportDeteils(ReportDetails details);
    /**
     *合同编号集合
     */
    List<String>  contractNumList();

    /**
     * 查询目录接口
     * @param param
     * @return
     */
    List<Map<String,Object>> getDirList();

    /**
     * 查询目录接口详情
     * @param param
     * @return
     */
    Map<String,Object> getDirListInfo(int id);

    /**
     * 历史报表详情数据
     * @param id
     * @return
     */
    List<Map<String,Object>> getDetailsList(int dirid);

    /**
     * 历史报表详情数据单条记录
     * @param id
     * @return
     */
    Map<String,Object> getDetailsInfo(int id);

    /**
     * 历史报表详情数据单条记录删除
     * @param id
     * @return
     */
    boolean detailsdel(int id);

    /**
     * 详情数据修改
     * @param datailsSaveParam
     * @return
     */
    int detailsEdit(ReportDetails datailsSaveParam);
}
