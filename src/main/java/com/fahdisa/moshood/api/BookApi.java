package com.fahdisa.moshood.api;


import com.fahdisa.moshood.entity.Book;
import com.fahdisa.moshood.entity.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("book")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class BookApi {

	@Path("{id}")
	@GET
	public Response getBook(@PathParam("id")Long id){
		return Response.ok(new Book()).build();
	}
}
