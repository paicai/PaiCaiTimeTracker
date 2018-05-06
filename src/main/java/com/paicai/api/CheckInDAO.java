package com.paicai.api;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class CheckInDAO extends AbstractDAO<CheckIn> {

    public CheckInDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<CheckIn> findAll() {
        return list(namedQuery("findAll"));
    }
}
