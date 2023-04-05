package com.tfswx.dbmigrate.core.constant;

import java.sql.Types;

/**
 * 实体字段表里定义的类型跟java.sql.Types里类型对照字典
 * 字段类型;（1:字符串 2:整数 3:浮点数 4:日期 5:二进制 6:大文本）
 * @author java
 * @date 2023-03-10 10:44:06
 */
public enum FieldDataTypeEnum {

    /**
     * 字符串
     */
    VARCHAR("1", Types.VARCHAR),
    /**
     * 整数
     */
    INTEGER("2", Types.INTEGER),
    /**
     * 浮点型
     */
    FLOAT("3", Types.FLOAT),
    /**
     * 日期
     */
    TIMESTAMP("4", Types.TIMESTAMP),
    /**
     * 二进制
     */
    BLOB("5", Types.BLOB),
    /**
     * 大文本
     */
    CLOB("6", Types.CLOB)
    ;
    /**
     * 获取采集方式编码
     * @return 采集方式编码
     */
    public String getZdlx() {
        return zdlx;
    }

    /**
     * 获取采集方式名称
     * @return 采集方式名称
     */
    public int getSqlType() {
        return sqlType;
    }




    /**
     * java.sql.Types里类型
     */
    private int sqlType;

    /**
     * 实体字段表里定义的类型
     */
    private String zdlx;

    /**
     * 初始化
     *
     * @param zdlx 实体字段表里定义的类型
     * @param sqlType java.sql.Types里类型
     */
    FieldDataTypeEnum(String zdlx,int sqlType) {
        this.sqlType = sqlType;
        this.zdlx = zdlx;
    }
}
