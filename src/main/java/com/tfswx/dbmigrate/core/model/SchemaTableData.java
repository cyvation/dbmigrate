package com.tfswx.dbmigrate.core.model;// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////

import java.util.List;

/**
 * 数据库表的数据
 *
 * @author tfswx
 */
public class SchemaTableData {

  private String schemaName;
  private String tableName;
  private List<String> columns;
  private List<List<Object>> rows;

  public String getSchemaName() {
    return schemaName;
  }

  public void setSchemaName(String schemaName) {
    this.schemaName = schemaName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public List<String> getColumns() {
    return columns;
  }

  public void setColumns(List<String> columns) {
    this.columns = columns;
  }

  public List<List<Object>> getRows() {
    return rows;
  }

  public void setRows(List<List<Object>> rows) {
    this.rows = rows;
  }
}
