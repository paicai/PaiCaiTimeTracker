package com.paicai;

import com.paicai.auth.TrackerAuthenticator;
import com.paicai.core.CheckIn;
import com.paicai.core.CheckInDAO;
import com.paicai.core.User;
import com.paicai.core.UserDAO;
import com.paicai.resources.TrackerResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.Authentication;
import org.hibernate.SessionFactory;

public class TrackerApplication extends Application<TrackerConfiguration> {

    private final HibernateBundle<TrackerConfiguration> hibernateBundle
            = new HibernateBundle<TrackerConfiguration>(CheckIn.class, User.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TrackerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new TrackerApplication().run(args);
    }

    @Override
    public String getName() {
        return "Tracker";
    }

    @Override
    public void initialize(final Bootstrap<TrackerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final TrackerConfiguration configuration,
                    final Environment environment) {
        final CheckInDAO checkInDAO = new CheckInDAO(hibernateBundle.getSessionFactory());
        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new UnitOfWorkAwareProxyFactory(hibernateBundle)
                                .create(TrackerAuthenticator.class, new Class<?>[]{UserDAO.class, SessionFactory.class},
                                        new Object[]{userDAO,
                                                hibernateBundle.getSessionFactory()}))
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));
        environment.jersey().register(new TrackerResource(checkInDAO, userDAO));
    }

}
