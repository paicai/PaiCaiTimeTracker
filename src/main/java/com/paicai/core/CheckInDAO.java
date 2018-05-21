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

    public List<CheckIn> findByUser(long userId) {
        return list(namedQuery("findByUser").setParameter("user_id", userId));
    }

    public CheckIn newCheckIn(long user_id, String type) {
        CheckIn checkIn = new CheckIn(DateTime.now(), type, user_id);
        currentSession().save(checkIn);
        currentSession().getTransaction().commit();

        return checkIn;
    }
}
