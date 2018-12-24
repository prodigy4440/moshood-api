package com.fahdisa.moshood.api;


import com.fahdisa.moshood.entity.Book;
import com.fahdisa.moshood.entity.Event;
import com.fahdisa.moshood.service.BookService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("book")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class BookApi {

	@Inject
	private BookService bookService;

	@POST
	public Response create(Book book){
		return Response.ok(bookService.createBook(book)).build();
	}

	@PUT
	public Response update(Book book){
		return Response.ok(bookService.updateBook(book)).build();
	}

	@Path("{id}")
	@GET
	public Response getBook(@PathParam("id")Long id){
		return Response.ok(bookService.getBook(id)).build();
	}

	@Path("{id}")
	@DELETE
	public Response deleteBook(@PathParam("id")Long id){
		return Response.ok(bookService.deleteBook(id)).build();
	}

	@Path("all")
	@GET
	public Response getBooks(@QueryParam("id")Long id,
							 @QueryParam("page")Integer page,
							 @QueryParam("size")Integer size){
		return Response.ok(bookService.getBooks(id, page, size)).build();
	}
}
