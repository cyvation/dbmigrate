// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.core.database;


import com.tfswx.dbmigrate.common.type.ProductTypeEnum;
import com.tfswx.dbmigrate.core.model.ColumnDescription;
import com.tfswx.dbmigrate.core.model.ColumnMetaData;
import com.tfswx.dbmigrate.core.model.SchemaTableData;
import com.tfswx.dbmigrate.core.model.TableDescription;

import java.sql.Connection;
import java.util.List;

/**
 * 数据库访问通用业务接口
 *
 * @author tfswx
 */
public interface IDatabaseInterface {

  /**
   * 获取数据库类型
   *
   * @return 数据库类型
   */
  ProductTypeEnum getDatabaseType();

  /**
   * 获取数据库的JDBC驱动类
   *
   * @return
   */
  String getDriverClassName();

  /**
   * 获取数据库的模式schema列表
   *
   * @param connection JDBC连接
   * @return 模式名列表
   */
  List<String> querySchemaList(Connection connection);

  /**
   * 获取指定模式Schema内的所有表列表
   *
   * @param connection JDBC连接
   * @param schemaName 模式名称
   * @return 表及视图名列表
   */
  List<TableDescription> queryTableList(Connection connection, String schemaName);

  /**
   * 精确获取表或视图的元数据
   *
   * @param connection JDBC连接
   * @param schemaName 模式名称
   * @param tableName  表或视图名称
   * @return
   */
  TableDescription queryTableMeta(Connection connection, String schemaName, String tableName);

  /**
   * 获取指定物理表的DDL语句
   *
   * @param connection JDBC连接
   * @param schemaName 模式名称
   * @param tableName  表名称
   * @return 字段元信息列表
   */
  String getTableDDL(Connection connection, String schemaName, String tableName);

  /**
   * 获取指定视图表的DDL语句
   *
   * @param connection JDBC连接
   * @param schemaName 模式名称
   * @param tableName  表或视图名称
   * @return 字段元信息列表
   */
  String getViewDDL(Connection connection, String schemaName, String tableName);

  /**
   * 获取指定模式表的字段列表
   *
   * @param connection JDBC连接
   * @param schemaName 模式名称
   * @param tableName  表或视图名称
   * @return 字段元信息列表
   */
  List<String> queryTableColumnName(Connection connection, String schemaName,
      String tableName);

  /**
   * 获取指定模式表的元信息
   *
   * @param connection JDBC连接
   * @param schemaName 模式名称
   * @param tableName  表或视图名称
   * @return 字段元信息列表
   */
  List<ColumnDescription> queryTableColumnMeta(Connection connection, String schemaName,
                                               String tableName);

  /**
   * 获取指定查询SQL的元信息
   *
   * @param connection JDBC连接
   * @param sql        SQL查询语句
   * @return 字段元信息列表
   */
  List<ColumnDescription> querySelectSqlColumnMeta(Connection connection, String sql);

  /**
   * 获取指定模式表的主键字段列表
   *
   * @param connection JDBC连接
   * @param schemaName 模式名称
   * @param tableName  表名称
   * @return 主键字段名称列表
   */
  List<String> queryTablePrimaryKeys(Connection connection, String schemaName, String tableName);

  /**
   * 获取指定模式表内的数据
   *
   * @param connection JDBC连接
   * @param schemaName 模式名称
   * @param tableName  表名称
   * @param rowCount   记录的行数
   * @return 数据内容
   */
  SchemaTableData queryTableData(Connection connection, String schemaName, String tableName,
                                 int rowCount);

  /**
   * 测试查询SQL语句的有效性
   *
   * @param connection JDBC连接
   * @param sql        待验证的SQL语句
   */
  void testQuerySQL(Connection connection, String sql);

  /**
   * 获取数据库的表全名
   *
   * @param schemaName 模式名称
   * @param tableName  表名称
   * @return 表全名
   */
  String getQuotedSchemaTableCombination(String schemaName, String tableName);

  /**
   * 获取字段列的结构定义
   *
   * @param v           值元数据定义
   * @param pks         主键字段名称列表
   * @param addCr       是否结尾换行
   * @param useAutoInc  是否自增
   * @param withRemarks 是否带有注释
   * @return 字段定义字符串
   */
  String getFieldDefinition(ColumnMetaData v, List<String> pks, boolean useAutoInc, boolean addCr,
                            boolean withRemarks);

  /**
   * 主键列转换为逗号分隔的字符串
   *
   * @param pks 主键字段列表
   * @return 主键字段拼接串
   */
  String getPrimaryKeyAsString(List<String> pks);

  /**
   * 获取表和字段的注释定义
   *
   * @param td  表信息定义
   * @param cds 列信息定义
   * @return 定义字符串列表
   */
  List<String> getTableColumnCommentDefinition(TableDescription td, List<ColumnDescription> cds);
}
