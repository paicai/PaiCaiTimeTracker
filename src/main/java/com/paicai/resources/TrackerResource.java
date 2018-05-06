package com.paicai.resources;

import com.paicai.api.CheckIn;
import com.paicai.api.CheckInDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/track")
@Produces(MediaType.APPLICATION_JSON)
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

}
