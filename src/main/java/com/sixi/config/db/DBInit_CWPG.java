package com.sixi.config.db;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class DBInit_CWPG {
    public DBInit_CWPG(){
        System.out.println("new DBInit_CWPG");
    }

    private final String MapperPath="classpath*:com/sixi/ktpd/mapper/cwpg/*.xml";
    private final String DataSourceBean="dataSource_cwpg";
    private final String SQLSessionFactoryBean="sqlSessionFactory_cwpg";
    private final String PackageName="com.sixi.domain.dao.cwpg";
    private final String txStr="cwpg";
    private final String pageHelper="postgresql";//不要乱写，可选值为oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012

    /**
     * 初始数据库
     * @param dbinfo
     * @return
     */
    @Bean(DataSourceBean)
    @Primary
    public DataSource dataSource(DBConfigInfo dbinfo){
        System.out.println("init bean:"+DataSourceBean);
        return DBConfigUtils.getDruid(dbinfo.getCwpg());
    }

    /**
     * 指定 mybaties mapper 的sql配置
     * @param driver
     * @return
     */
    @Primary
    @Bean(SQLSessionFactoryBean)
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier(DataSourceBean) DataSource driver){
        System.out.println(SQLSessionFactoryBean);
        return DBConfigUtils.getSQLSessionFactory(driver, MapperPath);
    }

    /**
     * 指定扫描 mybaties mapper 的包名
     * @return
     * @throws Exception
     */
    @Primary
    @Bean("MapperScanner_"+SQLSessionFactoryBean)
    public MapperScannerConfigurer mapperScannerConfigurer() throws Exception{
        System.out.println("MapperScanner_"+SQLSessionFactoryBean);
        return DBConfigUtils.getMapperScannerConfigurer(SQLSessionFactoryBean, PackageName);
    }

    @Transactional(txStr)
    public void setTransactional() { }

    /**
     * 数据库指定
     * @return
     */
    @Bean(pageHelper+"pageHelper")
    public PageHelper pageHelper(){
        return DBConfigUtils.pageHelper(pageHelper);
    }
}
