// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.common.constant;

/**
 * 常量定义
 *
 * @author tfswx
 */
public final class Const {

  /**
   * What's the file systems file separator on this operating system?
   */
  public static final String FILE_SEPARATOR = System.getProperty("file.separator");

  /**
   * What's the path separator on this operating system?
   */
  public static final String PATH_SEPARATOR = System.getProperty("path.separator");

  /**
   * CR: operating systems specific Carriage Return
   */
  public static final String CR = System.getProperty("line.separator");

  /**
   * DOSCR: MS-DOS specific Carriage Return
   */
  public static final String DOSCR = "\n\r";

  /**
   * An empty ("") String.
   */
  public static final String EMPTY_STRING = "";

  /**
   * The Java runtime version
   */
  public static final String JAVA_VERSION = System.getProperty("java.vm.version");

  /**
   * Create Table Statement Prefix String
   */
  public static final String CREATE_TABLE = "CREATE TABLE ";

  /**
   * Drop Table Statement Prefix String
   */
  public static final String DROP_TABLE = "DROP TABLE ";

  /**
   * Constant Keyword String
   */
  public static final String IF_NOT_EXISTS = "IF NOT EXISTS ";

  /**
   * Constant Keyword String
   */
  public static final String IF_EXISTS = "IF EXISTS ";

  public static final String ASC = "asc";

  public static final String DESC = "desc";
  //数据源类型-后端参数-在职单位编码
  public static final String  SJYLXHDCSZZDWBM = "zzdwbm";

  //字段属性-字段数据类型
  public static final String  ZDSX_ZDLX = "zdlx";

  //字段属性-字段名称
  public static final String  ZDSX_ZDMC = "zdmc";

  //字段属性-字段中文名称
  public static final String  ZDSX_ZDZWMC = "zdzwmc";

  //字段属性-字段长度
  public static final String  ZDSX_ZDCD = "zdcd";

  //字段属性-字段精度
  public static final String  ZDSX_ZDJD = "zdjd";
  /**
   * Constructor Function
   */
  private Const() {
  }

}
