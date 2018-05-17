package com.paicai.core;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;

import java.util.List;

public class CheckInDAO extends AbstractDAO<CheckIn> {

    public CheckInDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<CheckIn> findAll() {
        return list(namedQuery("findAll"));
    }

    public List<CheckIn> findByUser(String username) {
        return list(namedQuery("findByUser").setParameter("username", username));
    }

    public CheckIn newCheckIn(String type) {
        CheckIn checkIn = new CheckIn(DateTime.now(), type, Integer.valueOf(1));
        currentSession().save(checkIn);
        currentSession().getTransaction().commit();

        return checkIn;
    }
}
