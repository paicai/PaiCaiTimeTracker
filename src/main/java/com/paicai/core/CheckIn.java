package com.paicai.core;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "check_in")
@NamedQueries({
        @NamedQuery(name = "findAll",
                query = "select c from CheckIn c"),
        @NamedQuery(name = "findByUser",
                query = "select c from CheckIn c where user_id = :user_id"),
        @NamedQuery(name = "findByUserAndDate",
                query = "select c from CheckIn c where user_id = :user_id and DATE(check_in_time) = :date"),
        @NamedQuery(name = "findByUserAndWeek",
                query = "select c from CheckIn c where user_id = :user_id and DATE(check_in_time) between :dateMonday AND :dateSunday")
})
public class CheckIn {

    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHECK_IN_ID")
    private long id;

    /**
     * Time of check in
     */
    @Column(name ="CHECK_IN_TIME")
    private DateTime time;

    /**
     * Type of check in
     */
    @Column(name ="CHECK_IN_TYPE")
    private String type;

    /**
     * User ID
     */
    @Column(name ="USER_ID")
    private long userId;

//    /**
//     * User created
//     */
//    @GeneratedValue
//    @Column(name ="USER_CREATED")
//    private String userCreated;
//
//    /**
//     * Time created
//     */
//    @GeneratedValue
//    @Column(name ="TIME_CREATED")
//    private DateTime timeCreated;
//
//
//    /**
//     * User modified
//     */
//    @GeneratedValue
//    @Column(name ="USER_MODIFIED")
//    private String userModified;
//
//    /**
//     * Time modified
//     */
//    @GeneratedValue
//    @Column(name ="TIME_MODIFIED")
//    private DateTime timeModified;

    public CheckIn() {}

    public CheckIn(DateTime time, String type, long userId) {
        this.time = time;
        this.type = type;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

//    public String getUserCreated() {
//        return userCreated;
//    }
//
//    public void setUserCreated(String userCreated) {
//        this.userCreated = userCreated;
//    }
//
//    public DateTime getTimeCreated() {
//        return timeCreated;
//    }
//
//    public void setTimeCreated(DateTime timeCreated) {
//        this.timeCreated = timeCreated;
//    }
//
//    public String getUserModified() { return userModified; }
//
//    public void setUserModified(String userModified) {
//        this.userModified = userModified;
//    }
//
//    public DateTime getTimeModified() {
//        return timeModified;
//    }
//
//    public void setTimeModified(DateTime timeModified) {
//        this.timeModified = timeModified;
//    }
}
