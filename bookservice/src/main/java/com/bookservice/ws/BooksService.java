package com.bookservice.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bookservice.ws.model.Book;
import com.bookservice.ws.service.BooksServiceProvider;
import com.sun.jersey.spi.resource.Singleton;


@Path("/bookws")
@Singleton
public class BooksService {
	
	private BooksServiceProvider booksServiceProvider;
	
	
	public BooksService() {
		this.booksServiceProvider = new BooksServiceProvider();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/information")
	public Response welcome() {
		
		return Response.status(200).type(MediaType.APPLICATION_JSON).entity("Welcome to Books REST Service!").build();
		
	}

	@GET
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path("/books")
	public Response getBooks() {
		
		List<Book> books = this.booksServiceProvider.getBooks();
		
		GenericEntity<List<Book>> genericBookList = new GenericEntity<List<Book>>(books) {};
		
		return Response.status(200).type(MediaType.APPLICATION_JSON).entity(genericBookList).build();
	}
	
	@GET
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path("/books/{bookid}")
	public Response getBook(@PathParam("bookid") long bookId) {
		
		Book requestedBook = this.booksServiceProvider.findBookById(bookId);
		
		return Response.status(200).type(MediaType.APPLICATION_JSON).entity(requestedBook).build();
	}
	
	@POST
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path("/books")
	public Response saveBook(Book book) {
		
		Book savedBook = this.booksServiceProvider.saveBook(book);
		
		return Response.status(200).type(MediaType.APPLICATION_JSON).entity(savedBook).build();
	}
	
	@PUT
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path("/books/{bookid}")
	public Response updateBook(@PathParam("bookid") long bookId, Book modifiedBook) {
		
		modifiedBook = this.booksServiceProvider.updateBook(bookId, modifiedBook);
		
		return Response.status(200).type(MediaType.APPLICATION_JSON).entity(modifiedBook).build();
	}
	
	@DELETE
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path("/books/{bookid}")
	public Response updateBook(@PathParam("bookid") long bookId) {
		
		this.booksServiceProvider.deleteBook(bookId);
		
		return Response.status(200).type(MediaType.APPLICATION_JSON).entity("ID: " +bookId + " book was deleted !").build();
	}
	
}
