package com.paicai.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paicai.core.CheckIn;
import com.paicai.core.CheckInDAO;
import com.paicai.core.User;
import com.paicai.core.UserDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TrackerResource {

    private CheckInDAO checkInDAO;
    private UserDAO userDAO;

    public TrackerResource(CheckInDAO checkInDAO, UserDAO userDAO) {

        this.checkInDAO = checkInDAO;
        this.userDAO = userDAO;
    }

//    @GET
//    @UnitOfWork
//    public List<CheckIn> findAll() {
//        return checkInDAO.findAll();
//    }


    /*
    Whenever a person arrives or leaves a new checkIn is created, saving the userId, DateTime and CheckIn type
     */
    @POST
    @Path("/track")
    @UnitOfWork
    public Response newCheckIn(@Auth User user, String type) {
        return Response.created(UriBuilder.fromResource(TrackerResource.class).build()).entity(checkInDAO.newCheckIn(userDAO.getUser(user.getUsername()).getId(), type)).build();
    }

    /*
    Display all checkIns of a single user
     */
    @GET
    @Path("/track")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<CheckIn> findByUsername(@Auth User user) {

        if(null != user) {
            System.out.println(user.getUsername());
            return checkInDAO.findByUser(userDAO.getUser(user.getUsername()).getId());
        }
        System.out.println("user == null");
        return checkInDAO.findAll();
    }

    /*
    Register new user, pass JSON in POST method's body
    Ex. {"username": "testen", "password": "pass", "firstName": "Matej", "lastName": "Testen"}
     */
    @POST
    @UnitOfWork
    @Path("/register")
    public Response newUser(String user_data) throws IOException {

        //System.out.println("Data: " + user_data);
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(user_data, User.class);
        return Response.created(UriBuilder.fromResource(TrackerResource.class).build()).entity(userDAO.newUser(user)).build();
    }

//    @GET
//    @UnitOfWork
//    @Path("/register")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<User> findUsers() {
//        return userDAO.findAll();
//    }
}
