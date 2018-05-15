package com.paicai.resources;

import com.paicai.core.CheckIn;
import com.paicai.core.CheckInDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path("/track")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackerResource {

    private CheckInDAO checkInDAO;

    public TrackerResource(CheckInDAO checkInDAO) {
        this.checkInDAO = checkInDAO;
    }

    @GET
    @UnitOfWork
    public List<CheckIn> findAll() {
        return checkInDAO.findAll();
    }

    @POST
    @UnitOfWork
    public Response newCheckIn(String type) {
        return Response.created(UriBuilder.fromResource(TrackerResource.class).build()).entity(checkInDAO.newCheckIn(type)).build();
    }

}
