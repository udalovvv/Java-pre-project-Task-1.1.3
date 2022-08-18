package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;


public class Main {
    public static void main(String[] args) {

//        Session session = Util.getSessionFactory().openSession();

//        Util.getConnection();
        UserServiceImpl userService = new UserServiceImpl();

//        userService.createUsersTable();
//
//        userService.saveUser("Name1", "LastName1", (byte) 20);
//        userService.saveUser("Name2", "LastName2", (byte) 25);
//        userService.saveUser("Name3", "LastName3", (byte) 31);
//        userService.saveUser("Name4", "LastName4", (byte) 38);

//        userService.removeUserById(3);
//        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
