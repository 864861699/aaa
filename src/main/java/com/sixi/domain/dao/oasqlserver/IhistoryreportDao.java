package com.sixi.domain.dao.oasqlserver;

import com.sixi.domain.dto.historyreport.ReportListParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IhistoryreportDao {

    /**
     * 生成数据查询接口
     * @param param
     * @return
     */
    List<Map<String,Object>> getGenerate(ReportListParam param);

    /**
     * 生成单条数据查询接口
     * @param id
     * @return
     */
    Map<String,Object> getGenerateId(int id);

    /**
     * 款项浮动层
     * @param keyword 关键字
     * @return
     */
    List<Map<String,Object>> getmoneyfloat(String keyword);

    int sqltest();
}
