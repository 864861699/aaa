package com.sixi.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfigUtils {
    /**
     * 获取Druid实例，并自动初始配置
     * @param driver
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static DataSource getDruid(DBInfo dbinfo){
        System.out.println("Create DruidDataSource:"+dbinfo.getUrl());

        DruidDataSource dds = new DruidDataSource();
        dds.setUrl(dbinfo.getUrl());
        dds.setUsername(dbinfo.getUsername());
        dds.setPassword(dbinfo.getPassword());
        dds.setDriverClassName(dbinfo.getDriver());
        dds.setInitialSize(0);//初始化连接大小
        dds.setMaxActive(1500);//连接池最大使用连接数量
        dds.setMinIdle(0);//连接池最小空闲
        dds.setMaxWait(60000);//获取连接最大等待时间
        dds.setValidationQuery("select 1");//验证数据库连接有效性，要求查询语句

        //建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        dds.setTestWhileIdle(true);

        //申请连接时执行validationQuery检测连接是否有效，配置true会降低性能。
        dds.setTestOnBorrow(false);

        //归还连接时执行validationQuery检测连接是否有效，配置true会降低性能
        dds.setTestOnReturn(false);

        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dds.setTimeBetweenEvictionRunsMillis(60000);

        //配置一个连接在池中最小生存的时间，单位是毫秒
        dds.setMinEvictableIdleTimeMillis(25200000);

        //对于长时间不使用的连接强制关闭
        dds.setRemoveAbandoned(true);

        //关闭超过30分钟的空闲连接，1800秒，也就是30分钟
        dds.setRemoveAbandonedTimeout(1800);

        //关闭abanded连接时输出错误日志
        dds.setLogAbandoned(true);

        //监控数据库
//        dds.setFilters("mergeStat");
        try {
            dds.setFilters("stat,wall");
        } catch (SQLException e) {
            System.out.println("error:"+e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(dds.getDriverClassName());
        return dds;
    }

    /**
     * 指定 mybaties mapper 的sql配置
     * @param driver
     * @return
     */
    public static SqlSessionFactoryBean getSQLSessionFactory(DataSource driver, String mapperPath){
        SqlSessionFactoryBean s = new SqlSessionFactoryBean();
        s.setDataSource(driver);
        //s.setConfigLocation(new ClassPathResource("/mybatis-config-test.xml"));
        @SuppressWarnings("resource")
        ApplicationContext ctx=new FileSystemXmlApplicationContext();

        try {
            s.setMapperLocations(ctx.getResources(mapperPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }

    /**
     * 指定扫描 mybaties mapper 的包名
     * @return
     * @throws Exception
     */
    public static MapperScannerConfigurer getMapperScannerConfigurer(String sqlSessionFactoryBean, String packageName) throws Exception{
        MapperScannerConfigurer m=new MapperScannerConfigurer();
        m.setBasePackage(packageName);
        m.setSqlSessionFactoryBeanName(sqlSessionFactoryBean);
        return m;
    }

    /**
     * 指定分页数据库
     * @param pageHelper 数据库类型，可选值为oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012
     * @return
     */
    public static PageHelper pageHelper(String pageHelper){
        Properties p=new Properties();
        p.setProperty("dialect",pageHelper);
        p.setProperty("offsetAsPageNum","true");
        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("pageSizeZero","true");
        p.setProperty("reasonable","false");
        PageHelper page= new PageHelper();
        page.setProperties(p);
        return page;
    }
}
