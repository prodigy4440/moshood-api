package com.fahdisa.moshood.api;

import com.fahdisa.moshood.entity.Event;
import com.fahdisa.moshood.service.EventService;
import io.swagger.annotations.Api;
import org.wildfly.swarm.spi.runtime.annotations.Post;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("event")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "Event", description = "Event API")
public class EventApi {

    @Inject
    private EventService eventService;

    @Post
    public Response create(Event event){
        return Response.ok(eventService.createEvent(event)).build();
    }

    @PUT
    public Response update(Event event){
        return Response.ok(eventService.updateEvent(event)).build();
    }

    @Path("{id}")
    @DELETE
    public Response removeEvent(@PathParam("id")Long id){
        return Response.ok(eventService.removeEvent(id)).build();
    }

    @Path("{id}")
    @GET
    public Response getEvent(@PathParam("id")Long id){
        return Response.ok(eventService.getEvent(id)).build();
    }

    @Path("all")
    @GET
    public Response getEvents(@QueryParam("id")Long id,
                              @QueryParam("page")Integer page,
                              @QueryParam("size")Integer size){
        return Response.ok(eventService.getEvents(id, page, size)).build();
    }

}
