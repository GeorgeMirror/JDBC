package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        Util util = new Util();
        util.setConnection();
        connection = util.getConenction();
    }

    public void createUsersTable() {

        String createTable = "create table if not exists users" +
        "(" +
                "id int auto_increment primary key not null ," +
                "name VARCHAR(20) null," +
                "lastName varchar(20) null," +
                "age int null" +
        ")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTable);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE if exists `schea1`.`users`";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTable);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into users(name, lastName, age) values (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User с именем – " + name +" добавлен в базу данных");
    }

    public void removeUserById(long id) {
        StringBuilder builder = new StringBuilder ("DELETE FROM users WHERE id =");
        builder.append(id);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(builder.toString());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String select = "SELECT * FROM users";
        List<User> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(select);
            while (set.next()){
                User user = new User();
                user.setName(set.getString("name"));
                user.setLastName(set.getString("lastName"));
                user.setAge(set.getByte("age"));
                user.setId(set.getLong("id"));
                result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        this.dropUsersTable();
        this.createUsersTable();
    }
}
