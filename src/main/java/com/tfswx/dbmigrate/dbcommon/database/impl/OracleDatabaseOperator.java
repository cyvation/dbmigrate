// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.dbcommon.database.impl;

import com.tfswx.dbmigrate.dbcommon.database.AbstractDatabaseOperator;
import com.tfswx.dbmigrate.dbcommon.database.IDatabaseOperator;
import com.tfswx.dbmigrate.dbcommon.domain.StatementResultSet;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * Oracle数据库实现类
 *
 * @author tfswx
 */
public class OracleDatabaseOperator extends AbstractDatabaseOperator implements IDatabaseOperator {

    public OracleDatabaseOperator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getSelectTableSql(String schemaName, String tableName, List<String> fields) {
        return String.format("select %s from %s.%s ",
                StringUtils.join(fields, ","), schemaName, tableName);
    }

    @Override
    public StatementResultSet queryTableData(String schemaName, String tableName, List<String> fields, String condition,
                                                List<String> orders, String orderByType) {
        String sql = String.format("select %s from %s.%s where %s order by %s %s",
                StringUtils.join(fields, ","), schemaName, tableName, condition,
                StringUtils.join(orders, ","),
                orderByType);
        return this.queryBySql(sql, this.fetchSize);
    }

    @Override
    public StatementResultSet queryTableDataAsc(String schemaName, String tableName, List<String> fields, String condition,
                                                List<String> orders) {
        String sql = String.format("select %s from %s.%s where %s order by %s asc ",
                StringUtils.join(fields, ","), schemaName, tableName, condition,
                StringUtils.join(orders, ","));
        return this.queryBySql(sql, this.fetchSize);
    }

    @Override
    public StatementResultSet queryTableDataDesc(String schemaName, String tableName, List<String> fields, String condition, List<String> orders) {
        String sql = String.format("select %s from %s.%s where %s order by %s desc  ",
                StringUtils.join(fields, ","),
                schemaName, tableName, condition, StringUtils.join(orders, ","));
        return this.queryBySql(sql, this.fetchSize);
    }

    @Override
    public StatementResultSet queryTableData(String schemaName, String tableName,
                                             List<String> fields) {
        String sql = String.format("select %s from %s.%s ",
                StringUtils.join(fields, ","), schemaName, tableName);
        return this.queryBySql(sql, this.fetchSize);
    }

    @Override
    public void truncateTableData(String schemaName, String tableName) {
        String sql = String.format("TRUNCATE TABLE %s.%s ", schemaName, tableName);
        this.executeSql(sql);
    }

    @Override
    public void dropTable(String schemaName, String tableName) {
        String sql = String.format("DROP TABLE %s.%s ", schemaName, tableName);
        this.executeSql(sql);
    }
}
