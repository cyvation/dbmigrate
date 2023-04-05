// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.core.model;


import com.tfswx.dbmigrate.common.type.DBTableType;

/**
 * 数据库表描述符信息定义(Table Description)
 *
 * @author tfswx
 */
public class TableDescription {

  private String tableName;
  private String schemaName;
  private String remarks;
  private DBTableType tableType;

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getSchemaName() {
    return schemaName;
  }

  public void setSchemaName(String schemaName) {
    this.schemaName = schemaName;
  }

  public String getRemarks() {
    return this.remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getTableType() {
    return tableType.name();
  }

  public void setTableType(String tableType) {
    this.tableType = DBTableType.valueOf(tableType.toUpperCase());
  }

  public boolean isViewTable() {
    return DBTableType.VIEW == tableType;
  }
}
