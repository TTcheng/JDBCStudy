package com.wangchuncheng.test;

import com.wangchuncheng.ConnectionFactory;
import com.wangchuncheng.JDBCtest;
import com.wangchuncheng.entity.User;
import com.wangchuncheng.UserDaoImpl;
import com.wangchuncheng.dao.UserDao;

import java.sql.Connection;
import java.sql.SQLException;

public class UserTaoTest {
    public static Connection connection;
    public static UserDao userDao;
    public static void main(String[] args) {
        connection= ConnectionFactory.getInstance().makeConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userDao = new UserDaoImpl();

        User tom = new User();
        tom.setId(10);
        tom.setEmail("Tom@gmail.com");
        tom.setName("Tom");
        tom.setPassword("123456");

        JDBCtest.query();       //原始数据

        System.out.println("============插入Tom============");
        insetUser(tom);         //插入tom
        JDBCtest.query();

        System.out.println("============更新tom的邮箱============");
        tom.setEmail("Tom@126.com");
        updateUser(tom.getId(),tom);    //更新tom的邮箱
        JDBCtest.query();

        System.out.println("============删除Tom============");
        deleteUser(tom);             //删除tom
        JDBCtest.query();
    }
    public static void insetUser(User user){
        try {
            userDao.save(connection,user);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("========保存失败，已回滚========");
                connection.rollback();      //保存失败，回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            connection.commit();            //commit update
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteUser(User user){
        try {
            userDao.delete(connection,user);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("========删除失败，已回滚========");
                connection.rollback();      //保存失败，回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            connection.commit();            //commit update
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateUser(Long id, User user){
        try {
            userDao.update(connection,id,user);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("========保存失败，已回滚========");
                connection.rollback();      //保存失败，回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            connection.commit();            //commit update
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
