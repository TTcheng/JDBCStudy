package com.wangchuncheng.test;

import com.wangchuncheng.JDBCtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionTest {
    public static void main(String[] args) {
        Connection connection=null;
        connection = getConnection();
        try {
            connection.setAutoCommit(false);    //close autoCommit
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            insertUserData(connection);
            insertAddressData(connection);
        } catch (SQLException e) {
            System.out.println("===============捕获到SQL异常=============");
            e.printStackTrace();
            try {
                connection.rollback();
                System.out.println("===============事务回滚成功===============");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            if (connection!=null){
                try {
                    connection.commit();        //commit update
                    connection.close();         //close connection
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        JDBCtest.query();
    }
    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jsp_db","postgres","19960314");
        } catch (Exception e) {
            System.out.println("向数据库发起连接失败");
            e.printStackTrace();
        }
        return connection;
    }
    public static void insertUserData(Connection connection) throws SQLException{
        Statement st = null;
        String sql = "INSERT INTO tbl_user VALUES (10,'123456','Tom','tom@gmail.com')";
        st = connection.createStatement();
        int cnt = st.executeUpdate(sql);
        System.out.println("向用户表插入了"+cnt+"条记录");
    }
    public static void insertAddressData(Connection connection) throws SQLException{
        Statement st = null;
        String sql = "INSERT INTO tbl_address VALUES (1 ,'shanghai','China','10')";
        st = connection.createStatement();
        int cnt = st.executeUpdate(sql);
        System.out.println("向地址表插入了"+cnt+"条记录");
    }
}
