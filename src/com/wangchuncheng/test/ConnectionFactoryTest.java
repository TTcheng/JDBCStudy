package com.wangchuncheng.test;


import com.wangchuncheng.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTest {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory= ConnectionFactory.getInstance();
        Connection connection = connectionFactory.makeConnection();
        System.out.println(connection.getAutoCommit());
    }
}
