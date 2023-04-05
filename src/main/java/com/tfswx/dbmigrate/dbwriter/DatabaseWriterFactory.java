// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.dbwriter;

import com.tfswx.dbmigrate.common.type.ProductTypeEnum;
import com.tfswx.dbmigrate.core.utils.DatabaseAwareUtils;
import com.tfswx.dbmigrate.dbwriter.dm.DmWriterImpl;
import com.tfswx.dbmigrate.dbwriter.mysql.MySqlWriterImpl;
import com.tfswx.dbmigrate.dbwriter.oracle.OracleWriterImpl;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 数据库写入器构造工厂类
 *
 * @author tfswx
 */
public class DatabaseWriterFactory {

  private static final Map<ProductTypeEnum, Function<DataSource, IDatabaseWriter>> DATABASE_WRITER_MAPPER
      = new HashMap<ProductTypeEnum, Function<DataSource, IDatabaseWriter>>() {

    private static final long serialVersionUID = 3365136872693503697L;

    {
      put(ProductTypeEnum.MYSQL, MySqlWriterImpl::new);
      put(ProductTypeEnum.MARIADB, MySqlWriterImpl::new);
      put(ProductTypeEnum.ORACLE, OracleWriterImpl::new);
      put(ProductTypeEnum.DM, DmWriterImpl::new);
      put(ProductTypeEnum.GBASE8A, MySqlWriterImpl::new);
    }
  };

  /**
   * 获取指定数据库类型的写入器
   *
   * @param dataSource 连接池数据源
   * @return 写入器对象
   */
  public static IDatabaseWriter createDatabaseWriter(DataSource dataSource) {
    return DatabaseWriterFactory.createDatabaseWriter(dataSource, false);
  }

  /**
   * 获取指定数据库类型的写入器
   *
   * @param dataSource 连接池数据源
   * @param insert     对于GP/GP数据库来说是否使用insert引擎写入
   * @return 写入器对象
   */
  public static IDatabaseWriter createDatabaseWriter(DataSource dataSource, boolean insert) {
    ProductTypeEnum type = DatabaseAwareUtils.getDatabaseTypeByDataSource(dataSource);
    if (insert) {
      if (ProductTypeEnum.POSTGRESQL.equals(type) || ProductTypeEnum.GREENPLUM.equals(type)) {
        return new com.tfswx.dbmigrate.dbwriter.gpdb.GreenplumInsertWriterImpl(dataSource);
      }
    }

    if (!DATABASE_WRITER_MAPPER.containsKey(type)) {
      throw new RuntimeException(
          String.format("[dbwrite] Unsupported database type (%s)", type));
    }

    return DATABASE_WRITER_MAPPER.get(type).apply(dataSource);
  }

}
