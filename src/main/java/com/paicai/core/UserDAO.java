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

}
