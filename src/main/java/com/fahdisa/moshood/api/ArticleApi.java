package com.fahdisa.moshood.api;

import com.fahdisa.moshood.entity.Article;
import com.fahdisa.moshood.entity.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("article")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ArticleApi {

    @Path("{id}")
    @GET
    public Response getArticle(@PathParam("id")Long id){
        return Response.ok(new Article()).build();
    }
}