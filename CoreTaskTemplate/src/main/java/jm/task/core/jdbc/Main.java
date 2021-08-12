package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userDao = new UserServiceImpl();
        userDao.createUsersTable();
        userDao.saveUser("Ditrii", "Tupolev", (byte)32);
        userDao.saveUser("Anton", "Ivanov", (byte)53);
        userDao.saveUser("Pavel", "Petrov", (byte)21);
        userDao.saveUser("Sergei", "Sidorov", (byte)11);

        userDao.removeUserById((long)3);
        List<User> user = userDao.getAllUsers();
        for (User x : user) {
            System.out.println(x);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
