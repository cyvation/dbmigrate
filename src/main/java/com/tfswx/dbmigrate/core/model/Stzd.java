package com.tfswx.dbmigrate.core.model;

import lombok.Data;


/**
 * 查询采集实体列表-输出数据传输对象
 *
 * @author 肖富和
 * @date 2023-03-21 10:57:27
 */
@Data
@Deprecated
public class Stzd {

    //字段编号
    private String zdbh;

    //实体编号
    private String stbh;

    //字段名称(#原参数名称csmc)
    private String zdmc;

    //字段中文名称(#原参数中文名称cszwmc)
    private String zdzwmc;

    //校验规则
    private String jygz;

    //主键类型;唯一标识，父实体主键，数据主键  (#原参数类型)
    private String zjlx;

    //字段描述(原参数描述csms)
    private String zdms;

    //序号
    private Integer xh;

    //备注
    private String bz;

    //字段长度
    private String zdcd;

    //字段精度
    private String zdjd;

    //字段类型
    private String zdlx;
}
