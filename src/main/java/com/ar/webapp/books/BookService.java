/**
 * 
 */
package com.ar.webapp.books;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Alexander Rettner
 *
 */
@Path("book")
public class BookService {

	// BookDao bookDao;

	@Path("by_title")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Book getBook(BookRequest request) {
		// return bookDao.getBookByTitle(request.getTitle());
		Book book = new Book();
		book.setId(1);
		book.setTitle(request.getTitle());
		book.setAuthor("Leo Tolstoy");
		book.setPrice(49);
		return book;
	}

}