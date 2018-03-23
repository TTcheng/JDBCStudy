package com.wangchuncheng;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
    private static String driver;
    private static String dburl;
    private static String user;
    private static String password;

    private static final ConnectionFactory factory=new ConnectionFactory();
    private Connection connection;
    static {
        Properties prop = new Properties();
        InputStream inputStream = ConnectionFactory.class.getClassLoader()
                .getResourceAsStream("jdbcconfig.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println("==========读取配置文件出错==========");
        }
        driver = prop.getProperty("driver");
        dburl = prop.getProperty("dburl");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
    }

    public ConnectionFactory() {

    }
    public static ConnectionFactory getInstance(){
        return factory;
    }
    public Connection makeConnection(){
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(dburl,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
