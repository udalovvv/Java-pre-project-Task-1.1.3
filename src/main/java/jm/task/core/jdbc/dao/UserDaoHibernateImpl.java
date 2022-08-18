package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        Session session = getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS User " +
                "(id INT not null AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY (id))";
        session.createSQLQuery(sql).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS User";
        session.createSQLQuery(sql).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(new User(name, lastName, age));

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();

        List<User> users = session.createQuery("From User").getResultList();

        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();

        session.createSQLQuery("delete from user ").executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
}
