// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.dbwriter.gpdb;

import com.tfswx.dbmigrate.dbwriter.AbstractDatabaseWriter;
import com.tfswx.dbmigrate.dbwriter.IDatabaseWriter;
import com.tfswx.dbmigrate.dbwriter.util.ObjectCastUtils;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.List;

/**
 * Greenplum数据库Insert写入实现类
 *
 * @author tfswx
 */
@Slf4j
public class GreenplumInsertWriterImpl extends AbstractDatabaseWriter implements IDatabaseWriter {

  public GreenplumInsertWriterImpl(DataSource dataSource) {
    super(dataSource);
  }

  @Override
  protected String getDatabaseProductName() {
    return "Greenplum";
  }

  @Override
  public long write(List<String> fieldNames, List<Object[]> recordValues) {
    recordValues.parallelStream().forEach((Object[] row) -> {
      for (int i = 0; i < row.length; ++i) {
        try {
          row[i] = ObjectCastUtils.castByDetermine(row[i]);
        } catch (Exception e) {
          row[i] = null;
        }
      }
    });

    return super.write(fieldNames, recordValues);
  }
}
