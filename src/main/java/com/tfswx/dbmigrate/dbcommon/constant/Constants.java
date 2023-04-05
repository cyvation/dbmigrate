// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.dbcommon.constant;

/**
 * 常量值定义
 *
 * @author tfswx
 */
public final class Constants {

  /**
   * 默认的JDBC数据查询超时时间（单位：秒）
   */
  public static Integer DEFAULT_QUERY_TIMEOUT_SECONDS = 1 * 60 * 60;

  /**
   * 默认的fetch-size的值
   */
  public static int DEFAULT_FETCH_SIZE = 1000;

  /**
   * fetch-size的最小有效值
   */
  public static int MINIMUM_FETCH_SIZE = 100;
}
