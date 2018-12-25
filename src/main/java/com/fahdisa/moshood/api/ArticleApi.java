package com.fahdisa.moshood.api;

import com.fahdisa.moshood.entity.Article;
import com.fahdisa.moshood.entity.Event;
import com.fahdisa.moshood.entity.Section;
import com.fahdisa.moshood.service.ArticleService;
import io.swagger.annotations.Api;
import org.wildfly.swarm.spi.runtime.annotations.Post;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("article")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "Article", description = "Article API")
public class ArticleApi {

    @Inject
    private ArticleService articleService;

    @Post
    public Response create(Article article){
        return Response.ok(articleService.createArticle(article)).build();
    }

    @PUT
    public Response update(Article article){
        return Response.ok(articleService.updateArticle(article)).build();
    }

    @Path("{id}")
    @DELETE
    public Response delete(@PathParam("id")Long id){
        return Response.ok(articleService.removeArticle(id)).build();
    }

    @Path("{id}")
    @GET
    public Response getArticle(@PathParam("id")Long id){
        return Response.ok(articleService.getArticle(id)).build();
    }

    @Path("all")
    @GET
    public Response getArticles(@QueryParam("id")Long id,
                                @QueryParam("page")Integer page,
                                @QueryParam("size")Integer size){
        return Response.ok(articleService.getArticles(id, page, size)).build();
    }

    @Path("section/{id}")
    @Post
    public Response createSection(@PathParam("id")Long id, Section section){
        return Response.ok(articleService.createSection(id, section)).build();
    }

    @Path("section/{id}")
    @DELETE
    public Response removeSection(@PathParam("id")Long id){
        return Response.ok(articleService.removeSection(id)).build();
    }
}
