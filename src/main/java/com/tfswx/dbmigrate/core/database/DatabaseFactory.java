package com.tfswx.dbmigrate.core.database;// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////


import com.tfswx.dbmigrate.common.type.ProductTypeEnum;
import com.tfswx.dbmigrate.core.database.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 数据库实例构建工厂类
 *
 * @author tfswx
 */
public final class DatabaseFactory {

  private static final Map<ProductTypeEnum, Callable<AbstractDatabase>> DATABASE_MAPPER
      = new HashMap<ProductTypeEnum, Callable<AbstractDatabase>>() {

    private static final long serialVersionUID = 9202705534880971997L;

    {
      put(ProductTypeEnum.MYSQL, DatabaseMysqlImpl::new);
      put(ProductTypeEnum.ORACLE, DatabaseOracleImpl::new);
      put(ProductTypeEnum.DM, DatabaseDmImpl::new);
    }
  };

  public static AbstractDatabase getDatabaseInstance(ProductTypeEnum type) {
    Callable<AbstractDatabase> callable = DATABASE_MAPPER.get(type);
    if (null != callable) {
      try {
        return callable.call();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    throw new UnsupportedOperationException(
        String.format("Unknown database type (%s)", type.name()));
  }

  private DatabaseFactory() {
    throw new IllegalStateException();
  }

}
