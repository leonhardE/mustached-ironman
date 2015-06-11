/**
 * 
 */
package com.ar.webapp.books;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import com.jayway.restassured.response.Response;

/**
 * @author Alexander Rettner
 *
 */
public class BookServiceTest {

	/**
	 * Test method for {@link com.ar.webapp.books.BookService#getBook(com.ar.webapp.books.BookRequest)}.
	 */
	@Test
	public final void testGetBookByTitle() {
        BookRequest request = new BookRequest();
        request.setTitle("War and peace");
 
        Response response = given()
                                .contentType("application/json")
                                .body(request)
                                .log().all()
                            .expect()
                                .contentType("application/json")
                                .statusCode(Status.OK.getStatusCode())
                                .log().all()
                            .when()
                                .post("/calendar-webapp/webapi/book/by_title");

		Book book = response.as(Book.class);

		assertEquals(1, book.getId());
		assertEquals("Leo Tolstoy", book.getAuthor());
	}

}
