package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {}

    static private long id = 0;

    public void createUsersTable() {
//        String sql = "CREATE TABLE UsersTable " +
//                "(id MEDIUMINT not null AUTO_INCREMENT, " +
//                " name VARCHAR(255), " +
//                " lastName VARCHAR(255), " +
//                " age INTEGER, " +
//                " PRIMARY KEY (id))";

        String sql = "CREATE TABLE IF NOT EXISTS UsersTable " +
        "(id MEDIUMINT not null AUTO_INCREMENT, " +
        " name VARCHAR(255), " +
        " lastName VARCHAR(255), " +
        " age INTEGER, " +
        " PRIMARY KEY (id))";

        PreparedStatement preparedStatement = null;

        try (Connection connection = getConnection()) {
            Statement sta = connection.createStatement();
            sta.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS UsersTable;";
        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        id++;
        PreparedStatement preparedStatement = null;


//        String sql = "INSERT INTO UsersTable (id, name, lastName, age) VALUES(?, ?, ?, ?)";
        String sql = "INSERT INTO UsersTable (name, lastName, age) VALUES(?, ?, ?)";

        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql);

//            preparedStatement.setLong(1, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM UsersTable WHERE ID=?";

        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT id, name, lastName, age from UsersTable";

        Statement statement = null;
        try (Connection connection = getConnection()) {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                User user = new User();
                user.setId((int) resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM UsersTable";

        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
