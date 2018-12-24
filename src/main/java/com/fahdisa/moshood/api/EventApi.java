package com.fahdisa.moshood.api;

import com.fahdisa.moshood.entity.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("event")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class EventApi {

    @Path("{id}")
    @GET
    public Response getEvent(@PathParam("id")Long id){
        return Response.ok(new Event()).build();
    }

}