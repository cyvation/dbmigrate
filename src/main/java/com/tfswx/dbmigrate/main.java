package com.tfswx.dbmigrate;


import cn.hutool.core.util.StrUtil;
import com.tfswx.dbmigrate.common.type.ProductTypeEnum;
import com.tfswx.dbmigrate.core.database.AbstractDatabase;
import com.tfswx.dbmigrate.core.database.DatabaseFactory;
import com.tfswx.dbmigrate.core.model.ColumnDescription;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static com.tfswx.dbmigrate.core.utils.GenerateSqlUtils.getDDLCreateTableSQL;

/**
 * @Author 陈汪强
 * @date 2023-03-21 11:39
 */
public class main {

    private JdbcTemplate jdbcTemplate;

    public static void main0000(String[] args) {
        AbstractDatabase databaseInstance = DatabaseFactory.getDatabaseInstance(ProductTypeEnum.MYSQL);
        String dbUrl="jdbc:mysql://192.168.8.166:3306/cd_jyzbfx?useUnicode=true&characterEncoding=UTF8";
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(dbUrl, "root", "123456");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql="select * from t_zzjg_xt_dwbm where 1=2";
        List<ColumnDescription> ret = databaseInstance.querySelectSqlColumnMeta(connection, sql);

        List<String> createTableSQL = getDDLCreateTableSQL(
                ProductTypeEnum.ORACLE,
                ret,
                "SJGLPT",
                "T_DM_XT_FLDM",
                "T1",
                true);
        System.out.println(createTableSQL);


        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");//连接mysql
        dataSource.setUrl("jdbc:mysql://ip:3306");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);


        jdbcTemplate.batchUpdate(StrUtil.join(";",createTableSQL));
//        test();
    }


}