package com.sixi.config.db;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "dbconfig")
public class DBConfigInfo {

    private DBInfo testmysql;
    private DBInfo oamysql;
    private DBInfo oasqlserver;
    private DBInfo dyysqlserver;
    private DBInfo alidatasqlserver;
    private DBInfo oapg;
    private DBInfo cwpg;
}
