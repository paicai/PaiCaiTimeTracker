package com.paicai.core;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import java.sql.Date;

import java.util.List;

public class CheckInDAO extends AbstractDAO<CheckIn> {

    public CheckInDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<CheckIn> findAll() {
        return list(namedQuery("findAll"));
    }

    public List<CheckIn> findByUserAndDate(long userId, DateTime dateTime) {
        return list(namedQuery("findByUserAndDate").setParameter("user_id", userId).setParameter("date", new Date(dateTime.getMillis())));
    }

    public List<CheckIn> findByUserAndWeek(long userId, DateTime dateTime) {
        System.out.println("teden: "+ dateTime.weekOfWeekyear());
        DateTime monday = DateTime.now().withDayOfWeek(DateTimeConstants.MONDAY);
        DateTime sunday = monday.plusDays(6);

        System.out.println("monday: " + monday);
        System.out.println("sunday: " + sunday);

        return list(namedQuery("findByUserAndWeek").setParameter("user_id", userId).setParameter("dateMonday", new Date(monday.getMillis())).setParameter("dateSunday", new Date(sunday.getMillis())));
    }

//    public List<CheckIn> todayCheckIns(long userId) {
//        LocalDate.now();
//    }

    public CheckIn newCheckIn(long user_id, String type) {
        CheckIn checkIn = new CheckIn(roundDate(DateTime.now(), 5), type, user_id);
        currentSession().save(checkIn);
        currentSession().getTransaction().commit();

        return checkIn;
    }

    private DateTime roundDate(final DateTime dateTime, final int minutes) {
        if (minutes < 1 || 60 % minutes != 0) {
            throw new IllegalArgumentException("minutes must be a factor of 60");
        }

        final DateTime hour = dateTime.hourOfDay().roundFloorCopy();
        final long millisSinceHour = new Duration(hour, dateTime).getMillis();
        final int roundedMinutes = ((int)Math.round(
                millisSinceHour / 60000.0 / minutes)) * minutes;
        return hour.plusMinutes(roundedMinutes);
    }
}
