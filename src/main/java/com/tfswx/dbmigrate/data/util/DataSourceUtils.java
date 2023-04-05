// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.data.util;

import com.tfswx.dbmigrate.data.entity.SourceDataSourceProperties;
import com.tfswx.dbmigrate.data.entity.TargetDataSourceProperties;
import com.tfswx.dbmigrate.data.entity.Sjydy;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * DataSource工具类
 *
 * @author tfswx
 */
@Slf4j
public final class DataSourceUtils {

    public static final int MAX_THREAD_COUNT = 10;
    public static final int MAX_TIMEOUT_MS = 60000;

    private static HikariDataSource createDataSource(String ip, String port, String driver, String username, String password, String schemaName) {
        TargetDataSourceProperties properties = new TargetDataSourceProperties();
        properties.setIp(ip);
        properties.setPort(port);
        properties.setDriverClassName(driver);
        properties.setUsername(username);
        properties.setPassword(password);
        String url = getDbUrl(driver, ip, port,schemaName);
        if (driver.equals("mysql") && (!url.contains("?"))) {
            url = url + "?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        }
        properties.setUrl(url);
        return createTargetDataSource(properties);
    }

    public static HikariDataSource createDataSource(String driver, String url, String username, String password) {
        TargetDataSourceProperties properties = new TargetDataSourceProperties();
        properties.setUrl(url);
        properties.setDriverClassName(driver);
        properties.setUsername(username);
        properties.setPassword(password);
        return createTargetDataSource(properties);
    }

    public static HikariDataSource createDataSource(Sjydy sjydy) {
        TargetDataSourceProperties properties = new TargetDataSourceProperties();
        properties.setIp(sjydy.getIp());
        properties.setPort(sjydy.getPort());
        properties.setDriverClassName(sjydy.getDriver());
        properties.setUsername(sjydy.getUsername());
        properties.setPassword(sjydy.getPassword());
        String url = sjydy.getUrl();
        if (StringUtils.isBlank(sjydy.getUrl())) {
            url = getDbUrl(sjydy.getDriver(), sjydy.getIp(), sjydy.getPort(),sjydy.getMsm());
        }
//        if (sjydy.getDriver().contains("mysql")) {
//            if (StringUtils.isBlank(sjydy.getLjcs())) {
//                url = url + "?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
//            } else{
//                url = url + sjydy.getLjcs();
//            }
//        }
        properties.setUrl(url);
        return createTargetDataSource(properties);
    }

    public static String getDbUrl(String driverName, String ip, String port,String schemaName) {
        String url = "";
        if (driverName.contains("mysql")) {
            //mysql
            url = "jdbc:mysql://" + ip + ":" + port/*+"?useUnicode=true&characterEncoding=UTF8"*/;
            if(StringUtils.isNotBlank(schemaName)){
                url=url+"/"+schemaName;
            }
            url=url+  "?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        } else if (driverName.contains("oracle")) {
            //oracle
            url = "jdbc:oracle:thin:@" + ip + ":" + port + ":ORCL";
        } else if (driverName.contains("sqlserver")) {
            //sql server
            url = "jdbc:sqlserver://" + ip + ":" + port + ";integratedSecurity=true";
        } else if (driverName.contains("dm")) {
            //dm
            url = "jdbc:dm://" + ip + ":" + port;
            if(StringUtils.isNotBlank(schemaName)){
                url=url+"/"+schemaName;
            }
        }
        return url;
    }

    /**
     * 创建于指定数据库连接描述符的连接池
     *
     * @param properties 数据库连接描述符
     * @return HikariDataSource连接池
     */
    public static HikariDataSource createSourceDataSource(SourceDataSourceProperties properties) {
        HikariDataSource ds = new HikariDataSource();
        ds.setPoolName("The_Source_DB_Connection");
        ds.setJdbcUrl(properties.getUrl());
        ds.setDriverClassName(properties.getDriverClassName());
        ds.setUsername(properties.getUsername());
        ds.setPassword(properties.getPassword());
        if (properties.getDriverClassName().contains("oracle")) {
            ds.setConnectionTestQuery("SELECT 'Hello' from DUAL");
            // https://blog.csdn.net/qq_20960159/article/details/78593936
            System.getProperties().setProperty("oracle.jdbc.J2EE13Compliant", "true");
        } else if (properties.getDriverClassName().contains("db2")) {
            ds.setConnectionTestQuery("SELECT 1 FROM SYSIBM.SYSDUMMY1");
        } else {
            ds.setConnectionTestQuery("SELECT 1");
        }
        ds.setMaximumPoolSize(MAX_THREAD_COUNT);
        ds.setMinimumIdle(MAX_THREAD_COUNT);
        ds.setMaxLifetime(properties.getMaxLifeTime());
        ds.setConnectionTimeout(properties.getConnectionTimeout());
        ds.setIdleTimeout(MAX_TIMEOUT_MS);

        return ds;
    }

    /**
     * 创建于指定数据库连接描述符的连接池
     *
     * @param properties 数据库连接描述符
     * @return HikariDataSource连接池
     */
    public static HikariDataSource createTargetDataSource(TargetDataSourceProperties properties) {
        log.info("jdbcUrl:{}",properties.getUrl());
        if (properties.getUrl().trim().startsWith("jdbc:hive2://")) {
            throw new UnsupportedOperationException("Unsupported hive as target datasource!!!");
        }

        HikariDataSource ds = new HikariDataSource();
        ds.setPoolName("The_Target_DB_Connection");
        ds.setJdbcUrl(properties.getUrl());
        ds.setDriverClassName(properties.getDriverClassName());
        ds.setUsername(properties.getUsername());
        ds.setPassword(properties.getPassword());
        if (properties.getDriverClassName().contains("oracle")) {
            ds.setConnectionTestQuery("SELECT 'Hello' from DUAL");
        } else if (properties.getDriverClassName().contains("db2")) {
            ds.setConnectionTestQuery("SELECT 1 FROM SYSIBM.SYSDUMMY1");
        } else {
            ds.setConnectionTestQuery("SELECT 1");
        }
        ds.setMaximumPoolSize(MAX_THREAD_COUNT);
        ds.setMinimumIdle(MAX_THREAD_COUNT);
        ds.setMaxLifetime(properties.getMaxLifeTime());
        ds.setConnectionTimeout(properties.getConnectionTimeout());
        ds.setIdleTimeout(MAX_TIMEOUT_MS);

        // 如果是Greenplum数据库，这里需要关闭会话的查询优化器
        if (properties.getDriverClassName().contains("postgresql")) {
            org.springframework.jdbc.datasource.DriverManagerDataSource dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource();
            dataSource.setDriverClassName(properties.getDriverClassName());
            dataSource.setUrl(properties.getUrl());
            dataSource.setUsername(properties.getUsername());
            dataSource.setPassword(properties.getPassword());
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String versionString = jdbcTemplate.queryForObject("SELECT version()", String.class);
            if (Objects.nonNull(versionString) && versionString.contains("Greenplum")) {
                log.info(
                        "#### Target database is Greenplum Cluster, Close Optimizer now: set optimizer to 'off' ");
                ds.setConnectionInitSql("set optimizer to 'off'");
            }
        }

        return ds;
    }

    private DataSourceUtils() {
    }

}
