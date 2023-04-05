// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.dbwriter.dm;

import com.tfswx.dbmigrate.dbwriter.AbstractDatabaseWriter;
import com.tfswx.dbmigrate.dbwriter.IDatabaseWriter;
import com.tfswx.dbmigrate.dbwriter.util.ObjectCastUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * 达梦数据库写入实现类
 *
 * @author tfswx
 */
public class DmWriterImpl extends AbstractDatabaseWriter implements IDatabaseWriter {

  public DmWriterImpl(DataSource dataSource) {
    super(dataSource);
  }

  @Override
  protected String getDatabaseProductName() {
    return "DM";
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
