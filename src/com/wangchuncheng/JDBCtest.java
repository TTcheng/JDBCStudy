package com.wangchuncheng;

import java.sql.*;

public class JDBCtest {
    public static void main(String[] args) {
        query();
        insert();
        query();
        update();
        query();
        delete();
        query();
    }
    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jsp_db","postgres","19960314");
        } catch (SQLException e) {
            System.out.println("向数据库发起连接失败");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序失败");
            e.printStackTrace();
        }
    return connection;
    }
    public static void query(){
        Connection connection = getConnection();
        String sql = "SELECT * FROM tbl_user";
        Statement statement = null;
        try {
            statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("查询结果：");
            while (rs.next()){
                System.out.print(rs.getInt("id")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.print(rs.getString("password")+" ");
                System.out.print(rs.getString("email")+" ");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insert(){
        Connection connection = getConnection();
        String sql = "INSERT INTO tbl_user VALUES(default,'123546','Tom','tom@gmail.com')";
        Statement statement = null;
        try {
            statement=connection.createStatement();
            int cnt = statement.executeUpdate(sql);
            System.out.println("插入了"+cnt+"条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update(){
        Connection connection = getConnection();
        String sql = "UPDATE tbl_user SET email='tom@126.com' WHERE \"name\" = 'Tom'";
        Statement statement = null;
        try {
            statement=connection.createStatement();
            int cnt = statement.executeUpdate(sql);
            System.out.println("更新了"+cnt+"条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delete(){
        Connection connection = getConnection();
        String sql = "DELETE FROM tbl_user WHERE \"name\" = 'Tom'";
        Statement statement = null;
        try {
            statement=connection.createStatement();
            int cnt = statement.executeUpdate(sql);
            System.out.println("删除了"+cnt+"条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
