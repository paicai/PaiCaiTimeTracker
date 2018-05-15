package com.paicai;

import com.paicai.core.CheckIn;
import com.paicai.core.CheckInDAO;
import com.paicai.resources.TrackerResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TrackerApplication extends Application<TrackerConfiguration> {

    private final HibernateBundle<TrackerConfiguration> hibernateBundle
            = new HibernateBundle<TrackerConfiguration>(CheckIn.class) {
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
        environment.jersey().register(new TrackerResource(checkInDAO));
    }

}
