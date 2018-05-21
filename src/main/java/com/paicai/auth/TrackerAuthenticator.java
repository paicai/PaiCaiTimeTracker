package com.paicai.auth;

import com.paicai.core.User;
import com.paicai.core.UserDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;

import java.util.Optional;


public class TrackerAuthenticator implements Authenticator<BasicCredentials, User> {

    private final UserDAO userDAO;
    private final SessionFactory sessionFactory;

    public TrackerAuthenticator(UserDAO uDAO, SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.userDAO = uDAO;
    }

    @Override
    @UnitOfWork
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException{

        Session session = sessionFactory.openSession();

        try {

            ManagedSessionContext.bind(session);
            User user = userDAO.getUser(basicCredentials.getUsername());

            if(null != user && basicCredentials.getPassword().equals(user.getPassword())) {
                return Optional.of(user);
            }
            return Optional.empty();

        } catch (Exception e) {

        throw new AuthenticationException(e);

    } finally {

        ManagedSessionContext.unbind(sessionFactory);
        session.close();
    }



    }
}
