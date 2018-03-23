package com.wangchuncheng;

import com.wangchuncheng.dao.UserDao;
import com.wangchuncheng.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    /**
     * 保存用户信息
     * @param connection
     * @param user
     * @throws SQLException
     */
    @Override
    public void save(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("INSERT INTO tbl_user VALUES (?,?,?,?)");
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3,user.getName());
        preparedStatement.setString(4,user.getEmail());
        preparedStatement.execute();
    }

    /**
     * 根据指定id更新用户信息
     * @param connection
     * @param user
     * @throws SQLException
     */
    @Override
    public void update(Connection connection,Long id, User user) throws SQLException {
        String updateSql = "UPDATE tbl_user SET \"name\"=?,\"password\"=?,\"email\"=? WHERE \"id\"=?";
        PreparedStatement preparedStatement = connection.prepareCall(updateSql);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3,user.getEmail());
        preparedStatement.setLong(4,id);
        preparedStatement.execute();
    }

    /**
     * 根据删除指定用户信息
     * @param connection
     * @param user
     * @throws SQLException
     */
    @Override
    public void delete(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("DELETE FROM tbl_user WHERE \"id\" = ?");
        preparedStatement.setLong(1,user.getId());
        preparedStatement.execute();
    }
}
