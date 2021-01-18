package io.namoosori.travelclub.util;

import com.zaxxer.hikari.HikariDataSource;
import org.mariadb.jdbc.MariaDbPoolDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static MariaDbPoolDataSource ds;
    private static HikariDataSource hds;

    public static DataSource getDataSource(){
        if(hds == null)
            initHikari();
        return hds;
    }

    private static void initHikari(){
        Properties properties = new Properties();
        try {
            properties.load(ConnectionUtil.class.getResourceAsStream("/maria_info.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        hds = new HikariDataSource();
        hds.setMaximumPoolSize(20);
        hds.setDriverClassName(properties.getProperty("driverName"));
        hds.setJdbcUrl(String.format("jdbc:mariadb://%s:%s/%s",
                properties.getProperty("hostname"),
                properties.getProperty("port"),
                properties.getProperty("dbname")));
        hds.setUsername(properties.getProperty("username"));
        hds.setPassword(properties.getProperty("password"));
        hds.setAutoCommit(true);
    }

    private static void init(){
        Properties properties = new Properties();
        ds = new MariaDbPoolDataSource();

        try {
            ds.setServerName(properties.getProperty("hostname"));
            ds.setPort(Integer.parseInt(properties.getProperty("port")));
            ds.setDatabaseName(properties.getProperty("dbname"));
            ds.setUser(properties.getProperty("username"));
            ds.setPassword(properties.getProperty("password"));
            ds.setMaxPoolSize(20);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static Connection createConnection(){
        Connection connection = null;
        if(hds == null){
            initHikari();
        }

        try {
            connection = hds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }
}
