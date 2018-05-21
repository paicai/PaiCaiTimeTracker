package com.paicai.resources;

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
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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

    @POST
    @Path("/track")
    @UnitOfWork
    public Response newCheckIn(@Auth User user, String type) {
        return Response.created(UriBuilder.fromResource(TrackerResource.class).build()).entity(checkInDAO.newCheckIn(userDAO.getUser(user.getUsername()).getId(), type)).build();
    }

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

    @POST
    @UnitOfWork
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(String username) {

        User user = new User(username);
        return Response.created(UriBuilder.fromResource(TrackerResource.class).build()).entity(userDAO.newUser(user)).build();
    }

    @GET
    @UnitOfWork
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findUsers() {
        return userDAO.findAll();
    }
}
