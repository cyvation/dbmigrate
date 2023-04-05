// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.core.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.DynaBean;
import cn.hutool.core.util.StrUtil;
import com.tfswx.dbmigrate.common.type.ProductTypeEnum;
import com.tfswx.dbmigrate.core.database.AbstractDatabase;
import com.tfswx.dbmigrate.core.database.DatabaseFactory;
import com.tfswx.dbmigrate.core.model.ColumnDescription;
import com.tfswx.dbmigrate.core.model.ColumnMetaData;
import com.tfswx.dbmigrate.core.model.TableDescription;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tfswx.dbmigrate.common.constant.Const.*;

/**
 * 拼接SQL工具类
 *
 * @author tfswx
 */
public final class GenerateSqlUtils {


    public static List<ColumnDescription> convertStzdToColumDesc(List<Object> stzdList) {
        List<ColumnDescription> columns = new ArrayList<>();
        stzdList.forEach(sjcjXtStzd -> {
            DynaBean dynaBean = BeanUtil.createDynaBean(sjcjXtStzd);
            ColumnDescription columnDescription = new ColumnDescription();
            columnDescription.setFieldName(dynaBean.get(ZDSX_ZDMC));
            columnDescription.setRemarks(dynaBean.get(ZDSX_ZDZWMC));
            //字段长度
            if (StrUtil.isNotBlank(dynaBean.safeGet(ZDSX_ZDCD))) {
                columnDescription.setPrecisionSize(Integer.parseInt(dynaBean.get(ZDSX_ZDCD)));
                columnDescription.setDisplaySize(Integer.parseInt(dynaBean.get(ZDSX_ZDCD)));
            }
            //字段精度
            if (StrUtil.isNotBlank(dynaBean.safeGet(ZDSX_ZDJD))) {
                columnDescription.setScaleSize(Integer.parseInt(dynaBean.get(ZDSX_ZDJD)));
            }
            if (StrUtil.isNotBlank(dynaBean.safeGet(ZDSX_ZDLX))) {
                columnDescription.setFieldType(Integer.parseInt(dynaBean.get(ZDSX_ZDLX)));
            } else {
                //如果没设置字段类型，默认使用字符串
                columnDescription.setFieldType(Types.VARCHAR);
                columnDescription.setPrecisionSize(4000);
                columnDescription.setDisplaySize(4000);
            }
            columnDescription.setSigned(false);
            columns.add(columnDescription);
        });
        return columns;
    }

/*    public static String getDDLCreateTableSQL(
            AbstractDatabase db,
            String schemaName,
            String tableName,
            List<Stzd> stzdList,
            boolean withRemarks,
            String tableRemarks,
            boolean autoIncr) {
        return getDDLCreateTableSQL(db, convertStzdToColumDesc(stzdList), schemaName, tableName, withRemarks, tableRemarks, autoIncr);
    }*/

    /**
     * 生成ddl语句
     *
     * @param db           数据库
     * @param fieldNames
     * @param schemaName
     * @param tableName
     * @param withRemarks
     * @param tableRemarks
     * @param autoIncr
     * @return
     */
    public static String getDDLCreateTableSQL(
            AbstractDatabase db,
            List<ColumnDescription> fieldNames,
            String schemaName,
            String tableName,
            boolean withRemarks,
            String tableRemarks,
            boolean autoIncr) {
        ProductTypeEnum type = db.getDatabaseType();
        if (ProductTypeEnum.DM.equals(type) && StringUtils.isNotBlank(schemaName)) {
            schemaName = schemaName.toUpperCase();
            fieldNames.forEach(col -> {
                col.setFieldName(col.getFieldName().toUpperCase());
            });
        }
        StringBuilder sb = new StringBuilder();
//    List<String> pks = fieldNames.stream()
//        .filter((cd) -> primaryKeys.contains(cd.getFieldName()))
//        .map((cd) -> cd.getFieldName())
//        .collect(Collectors.toList());

        sb.append(CREATE_TABLE);
        // if(ifNotExist && type!=DatabaseType.ORACLE) {
        // sb.append( Const.IF_NOT_EXISTS );
        // }
        sb.append(db.getQuotedSchemaTableCombination(schemaName, tableName));
        sb.append("(");

        for (int i = 0; i < fieldNames.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            } else {
                sb.append("  ");
            }

            ColumnMetaData v = fieldNames.get(i).getMetaData();
            sb.append(db.getFieldDefinition(v, new ArrayList<>(), autoIncr, false, withRemarks));
        }

//    if (!pks.isEmpty()) {
//      String pk = db.getPrimaryKeyAsString(pks);
//      sb.append(", PRIMARY KEY (").append(pk).append(")");
//    }

        sb.append(")");
        if (ProductTypeEnum.MYSQL == type) {
            sb.append("ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin");
            if (withRemarks && StringUtils.isNotBlank(tableRemarks)) {
                sb.append(String.format(" COMMENT='%s' ", tableRemarks.replace("'", "\\'")));
            }
        }

        return DDLFormatterUtils.format(sb.toString());
    }

    /**
     * 生成ddl语句
     *
     * @param type       数据库类型
     * @param fieldNames 列字段
     * @param schemaName 模式名
     * @param tableName  表名
     * @param autoIncr   是否自增
     * @return
     * @return
     */
    public static String getDDLCreateTableSQL(
            ProductTypeEnum type,
            List<ColumnDescription> fieldNames,
            String schemaName,
            String tableName,
            boolean autoIncr) {
        return getDDLCreateTableSQL(
                DatabaseFactory.getDatabaseInstance(type),
                fieldNames,
                schemaName,
                tableName,
                false,
                null,
                autoIncr);
    }

    /**
     * 生成含描述的ddl语句
     *
     * @param type         数据库类型
     * @param fieldNames   列字段
     * @param schemaName   模式名
     * @param tableName    表名
     * @param tableRemarks 表描述
     * @param autoIncr     是否自增
     * @return
     */
    public static List<String> getDDLCreateTableSQL(
            ProductTypeEnum type,
            List<ColumnDescription> fieldNames,
            String schemaName,
            String tableName,
            String tableRemarks,
            boolean autoIncr) {
        AbstractDatabase db = DatabaseFactory.getDatabaseInstance(type);
        String createTableSql = getDDLCreateTableSQL(db, fieldNames, schemaName,
                tableName, true, tableRemarks, autoIncr);
        if (type.noCommentStatement()) {
            return Arrays.asList(createTableSql);
        }

        TableDescription td = new TableDescription();
        td.setSchemaName(schemaName);
        td.setTableName(tableName);
        td.setRemarks(tableRemarks);
        td.setTableType("TABLE");
        List<String> results = db.getTableColumnCommentDefinition(td, fieldNames);
        results.add(0, createTableSql);
        return results;
    }


    public static Boolean executeUpdate(List<String> sqls, Connection connection) {
        boolean isse = true;
        try {
            for (String sql : sqls) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.executeUpdate();
                //6、关闭连接
                ps.close();
            }
            connection.close();
        } catch (SQLException throwables) {
            isse = false;
            throwables.printStackTrace();
        }
        return isse;
    }

    private GenerateSqlUtils() {
        throw new IllegalStateException();
    }

}
