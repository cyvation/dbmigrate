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

import javax.sql.DataSource;

/**
 * DM数据库实现类
 *
 * @author tfswx
 */
public class DmDatabaseOperator extends OracleDatabaseOperator {

  public DmDatabaseOperator(DataSource dataSource) {
    super(dataSource);
  }

}
