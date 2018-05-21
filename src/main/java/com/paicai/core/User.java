package com.paicai.core;


import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "findUser",
                query = "select u from User u where username = :username"),
        @NamedQuery(name = "findAllUsers",
                query = "select u from User u")
})
public class User implements Principal {


    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long id;

    /**
     * Username
     */
    @Column(name ="USERNAME")
    private String username;

    /**
     * Password
     */
    @Column(name ="PASSWORD")
    private String password;

    /**
     * First Name
     */
    @Column(name ="FIRST_NAME")
    private String firstName;

    /**
     * Last Name
     */
    @Column(name ="LAST_NAME")
    private String lastName;

    /**
     * User who confirms vacations or absence
     */
//    @Column(name = "USER_ID_CONFIRM")
//    private long userIdConfirm;

    public User() {};

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public long getUserIdConfirm() {
//        return userIdConfirm;
//    }
//
//    public void setUserIdConfirm(long userIdConfirm) {
//        this.userIdConfirm = userIdConfirm;
//    }

    public String getName() {
        return this.username;
    }
}
