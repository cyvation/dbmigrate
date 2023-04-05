// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.dbcommon.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC连接及结果集实体参数定义类
 *
 * @author tfswx
 */
@Slf4j
@Data
public class StatementResultSet implements AutoCloseable {

  private boolean isAutoCommit;
  private Connection connection;
  private Statement statement;
  private ResultSet resultset;

  @Override
  public void close() {
    try {
      connection.setAutoCommit(isAutoCommit);
    } catch (SQLException e) {
      log.warn("Jdbc Connect setAutoCommit() failed, error: {}", e.getMessage());
    }

    JdbcUtils.closeResultSet(resultset);
    JdbcUtils.closeStatement(statement);
    JdbcUtils.closeConnection(connection);
  }
}
