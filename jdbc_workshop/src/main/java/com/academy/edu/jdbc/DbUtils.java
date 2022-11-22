package com.academy.edu.jdbc;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class DbUtils{
    private static final DataSource DATASOURCE;

    private DbUtils() {
    }

    public static DataSource getDataSource() {
        return DATASOURCE;
    }

    static {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://133.186.151.141/nhn_academy_12");
        basicDataSource.setUsername("nhn_academy_12");
        basicDataSource.setPassword("jcR59.XEQFn[ES6o");
        basicDataSource.setInitialSize(2);
        basicDataSource.setMaxTotal(2);
        basicDataSource.setTestOnBorrow(true);
        basicDataSource.setMaxWaitMillis(1000);

        DATASOURCE = basicDataSource;
    }
}
