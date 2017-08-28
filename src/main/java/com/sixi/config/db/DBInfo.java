package com.sixi.config.db;

import lombok.Data;

@Data
public class DBInfo {
    private String driver;
    private String url;
    private String username;
    private String password;
}
