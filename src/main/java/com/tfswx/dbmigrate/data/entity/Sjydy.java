package com.tfswx.dbmigrate.data.entity;

import lombok.Data;

import java.util.Date;

/**
 * 数据源定义
 *
 * @author 顾鹏
 * @date 2023-03-10 14:53:59
 */
@Data
public class Sjydy {
    /**
     * 数据源编号
     */
    private String sjybh;

    /**
     * 数据源名称
     */
    private String sjymc;

    /**
     * 数据源分类编号
     */
    private String sjyflbh;

    /**
     * 数据源类型代码
     */
    private String sjylxdm;

    /**
     * 数据源类型名称
     */
    private String sjylxmc;

    /**
     * 数据库驱动
     */
    private String driver;

    /**
     * IP
     */
    private String ip;

    /**
     * 端口
     */
    private String port;

    /**
     * URL
     */
    private String url;

    /**
     * 账号;
     */
    private String username;

    /**
     * 密码;
     */
    private String password;

    /**
     * 连接参数（扩展字段）
     */
    private String ljcs;

    /**
     * 备注
     */
    private String bz;

    /**
     * 创建人名称
     */
    private String cjrmc;

    /**
     * 创建人编码
     */
    private String cjrbm;

    /**
     * 是否删除
     */
    private String sfsc;

    /**
     * 创建时间
     */
    private Date cjsj;

    /**
     * 最后修改时间
     */
    private Date zhxgsj;

    /**
     * 模式名
     */
    private String msm;

}