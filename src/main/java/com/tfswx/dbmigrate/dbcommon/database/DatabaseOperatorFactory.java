// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.dbcommon.database;

import com.tfswx.dbmigrate.common.type.ProductTypeEnum;
import com.tfswx.dbmigrate.core.utils.DatabaseAwareUtils;
import com.tfswx.dbmigrate.dbcommon.database.impl.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 数据库操作器构造工厂类
 *
 * @author tfswx
 */
public final class DatabaseOperatorFactory {

  private static final Map<ProductTypeEnum, Function<DataSource, IDatabaseOperator>> DATABASE_OPERATOR_MAPPER
      = new HashMap<ProductTypeEnum, Function<DataSource, IDatabaseOperator>>() {

    private static final long serialVersionUID = -5278835613240515265L;

    {
      put(ProductTypeEnum.MYSQL, MysqlDatabaseOperator::new);
      put(ProductTypeEnum.MARIADB, MysqlDatabaseOperator::new);
      put(ProductTypeEnum.ORACLE, OracleDatabaseOperator::new);
      put(ProductTypeEnum.DM, DmDatabaseOperator::new);
      put(ProductTypeEnum.GBASE8A, MysqlDatabaseOperator::new);
    }
  };

  /**
   * 根据数据源获取数据的读取操作器
   *
   * @param dataSource 数据库源
   * @return 指定类型的数据库读取器
   */
  public static IDatabaseOperator createDatabaseOperator(DataSource dataSource) {
    ProductTypeEnum type = DatabaseAwareUtils.getDatabaseTypeByDataSource(dataSource);
    if (!DATABASE_OPERATOR_MAPPER.containsKey(type)) {
      throw new RuntimeException(
          String.format("[dbcommon] Unsupported database type (%s)", type));
    }

    return DATABASE_OPERATOR_MAPPER.get(type).apply(dataSource);
  }

}
