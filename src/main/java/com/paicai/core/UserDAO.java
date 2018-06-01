package com.paicai.core;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User getUser(String username) {
        List<User> userList = list(namedQuery("findUser").setParameter("username", username));

        if(userList.size() == 1) {
            return userList.get(0);
        }
        return null;
    }

    public List<User> findAll() {
        List<User> userList = list(namedQuery("findAllUsers"));
        return userList;
    }

    public User newUser(User user) {
//        User user = new User(username, password, firstName, lastName);
//        System.out.println(user.getUsername());
        currentSession().save(user);
        currentSession().getTransaction().commit();
        return user;
    }

}
